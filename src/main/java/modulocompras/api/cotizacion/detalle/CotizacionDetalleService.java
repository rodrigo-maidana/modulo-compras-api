package modulocompras.api.cotizacion.detalle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import modulocompras.api.cotizacion.Cotizacion;
import modulocompras.api.cotizacion.CotizacionService;
import modulocompras.api.producto.Producto;
import modulocompras.api.producto.ProductoService;

@Service
public class CotizacionDetalleService {

    @Autowired
    private CotizacionDetalleRepository cotizacionDetalleRepository;

    @Autowired
    @Lazy
    private CotizacionService cotizacionService;

    @Autowired
    private ProductoService productoService;

    public CotizacionDetalleService(CotizacionDetalleRepository cotizacionDetalleRepository) {
        this.cotizacionDetalleRepository = cotizacionDetalleRepository;
    }

    public List<CotizacionDetalleDTO> getAllCotizacionesDetalles() {
        return cotizacionDetalleRepository.findByEliminadoFalse().stream()
                .map(CotizacionDetalleDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<CotizacionDetalle> getCotizacionDetalleById(Integer id) {
        return cotizacionDetalleRepository.findByIdAndEliminadoFalse(id);
    }

    public void createCotizacionDetalle(Integer idCotizacion, Integer idProducto,
            Integer cantidad) {
        Cotizacion cotizacion = cotizacionService.getCotizacionById(idCotizacion)
                .orElseThrow(() -> new IllegalArgumentException("Cotizacion no encontrada"));
        Producto producto = productoService.getProductoById(idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        CotizacionDetalle newCotizacionDetalle = new CotizacionDetalle();
        newCotizacionDetalle.setCotizacion(cotizacion);
        newCotizacionDetalle.setProducto(producto);
        newCotizacionDetalle.setCantidad(cantidad);
        newCotizacionDetalle.setPrecioUnitario(0.0); // Asegúrate de que esto sea correcto y no deba ser modificado

        cotizacionDetalleRepository.save(newCotizacionDetalle);
    }

    public Optional<CotizacionDetalleDTO> updateCotizacionDetalle(Integer id,
            CotizacionDetalleDTO cotizacionDetalleDTO) {
        return cotizacionDetalleRepository.findByIdAndEliminadoFalse(id)
                .map(existingCotizacionDetalle -> {
                    existingCotizacionDetalle.setCantidad(cotizacionDetalleDTO.getCantidad());

                    Optional<Producto> optionalProducto = productoService
                            .getProductoById(cotizacionDetalleDTO.getProducto().getId());

                    if (optionalProducto.isPresent())
                        existingCotizacionDetalle.setProducto(optionalProducto.get());

                    CotizacionDetalle updatedCotizacionDetalle = cotizacionDetalleRepository
                            .save(existingCotizacionDetalle);
                    return new CotizacionDetalleDTO(updatedCotizacionDetalle);
                });
    }

    public boolean deleteCotizacionDetalle(Integer id) {
        return cotizacionDetalleRepository.findByIdAndEliminadoFalse(id)
                .map(cotizacionDetalle -> {
                    cotizacionDetalle.setEliminado(true);
                    cotizacionDetalleRepository.save(cotizacionDetalle);
                    return true;
                }).orElse(false);
    }

    public List<CotizacionDetalle> getCotizacionDetallesById(Integer id) {
        return cotizacionDetalleRepository.findByCotizacionIdAndEliminadoFalse(id);
    }
}
