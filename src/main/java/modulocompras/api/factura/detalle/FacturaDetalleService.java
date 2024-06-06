package modulocompras.api.factura.detalle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import modulocompras.api.factura.Factura;
import modulocompras.api.factura.FacturaRepository;
import modulocompras.api.factura.FacturaService;
import modulocompras.api.producto.Producto;
import modulocompras.api.producto.ProductoService;

@Service
public class FacturaDetalleService {

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    @Lazy
    private FacturaService facturaService;

    @Autowired
    private ProductoService productoService;

    // Método para obtener todos los detalles de facturas
    public List<FacturaDetalle> getAllFacturasDetalles() {
        return facturaDetalleRepository.findByEliminadoFalse();
    }

    // Método para obtener un detalle de factura por su ID
    public Optional<FacturaDetalle> getFacturaDetalleById(Integer id) {
        return facturaDetalleRepository.findByIdAndEliminadoFalse(id);
    }

    // Método para crear un detalle de factura
    public Optional<FacturaDetalle> createFacturaDetalle(Integer id, FacturaDetalleDTO facturaDetalleDTO) {

        Factura factura = facturaService.getFacturaById(id).orElse(null);
        if (factura == null) {
            return Optional.empty();
        }

        Producto producto = productoService.getProductoById(facturaDetalleDTO.getProducto().getId()).orElse(null);
        if (producto == null) {
            return Optional.empty();
        }

        List<FacturaDetalle> detalles = getDetallesByFacturaId(id);

        for (FacturaDetalle detalle : detalles) {
            if (detalle.getProducto().getId().equals(facturaDetalleDTO.getProducto().getId())) {
                return Optional.empty();
            }
        }

        FacturaDetalle facturaDetalle = new FacturaDetalle(facturaDetalleDTO);
        facturaDetalle.setFactura(factura);

        Double totalDetalle = facturaDetalleDTO.getPrecioUnitario() * facturaDetalleDTO.getCantidad();
        factura.setMontoTotal(factura.getMontoTotal() + totalDetalle);
        factura.setSaldoPendiente(factura.getSaldoPendiente() + totalDetalle);

        facturaRepository.save(factura);

        return Optional.of(facturaDetalleRepository.save(facturaDetalle));
    }

    // Método para actualizar un detalle de factura
    public Optional<FacturaDetalle> updateFacturaDetalle(Integer id, FacturaDetalleDTO facturaDetalleDTO) {
        FacturaDetalle facturaDetalle = getFacturaDetalleById(id).orElse(null);
        if (facturaDetalle == null) {
            return Optional.empty();
        }

        Producto producto = productoService.getProductoById(facturaDetalleDTO.getProducto().getId()).orElse(null);
        if (producto == null) {
            return Optional.empty();
        }

        return Optional.of(facturaDetalleRepository.save(facturaDetalle));
    }

    // Método para eliminar un detalle de factura
    public boolean deleteFacturaDetalle(Integer id) {
        FacturaDetalle facturaDetalle = getFacturaDetalleById(id).orElse(null);
        if (facturaDetalle == null) {
            return false;
        }

        facturaDetalle.setEliminado(true);
        facturaDetalleRepository.save(facturaDetalle);

        return true;
    }

    // Método para obtener todos los detalles de una factura
    public List<FacturaDetalle> getDetallesByFacturaId(Integer idFactura) {
        return facturaDetalleRepository.findByFacturaIdAndEliminadoFalse(idFactura);
    }

}
