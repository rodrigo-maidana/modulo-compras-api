package modulocompras.api.pedido_compra.detalle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import modulocompras.api.cotizacion.Cotizacion;
import modulocompras.api.cotizacion.CotizacionService;
import modulocompras.api.cotizacion.detalle.CotizacionDetalle;
import modulocompras.api.cotizacion.detalle.CotizacionDetalleService;
import modulocompras.api.pedido_compra.PedidoCompra;
import modulocompras.api.pedido_compra.PedidoCompraDTO;
import modulocompras.api.pedido_compra.PedidoCompraService;
import modulocompras.api.producto.Producto;
import modulocompras.api.producto.ProductoService;
import modulocompras.api.proveedor.ProveedorDTO;

@Service
public class PedidoDetalleService {

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;

    @Autowired
    private PedidoCompraService pedidoCompraService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    @Lazy
    private CotizacionService cotizacionService;

    @Autowired
    private CotizacionDetalleService cotizacionesDetallesService;

    public List<PedidoDetalle> getAllPedidosDetalles() {
        return pedidoDetalleRepository.findByEliminadoFalse();
    }

    public Optional<PedidoDetalleDTO> getPedidoDetalleById(Integer id) {
        return pedidoDetalleRepository.findByIdAndEliminadoFalse(id)
                .map(PedidoDetalleDTO::new);
    }

    public Optional<PedidoDetalleDTO> createPedidoDetalle(Integer id, PedidoDetalleDTO pedidoDetalleDTO) {
        Producto producto = productoService.getProductoById(pedidoDetalleDTO.getProducto().getId()).orElse(null);
        if (producto == null) {
            return Optional.empty();
        }

        Optional<PedidoCompraDTO> optionalPedidoCompraDTO = pedidoCompraService.getPedidoCompraById(id);
        if (!optionalPedidoCompraDTO.isPresent()) {
            return Optional.empty();
        }

        PedidoCompra pedidoCompra = new PedidoCompra(optionalPedidoCompraDTO.get());
        Integer cantidad = pedidoDetalleDTO.getCantidad();

        PedidoDetalle newPedidoDetalle = new PedidoDetalle();
        newPedidoDetalle.setProducto(producto);
        newPedidoDetalle.setPedidoCompra(pedidoCompra);
        newPedidoDetalle.setCantidad(cantidad);

        PedidoDetalle savedPedidoDetalle = pedidoDetalleRepository.save(newPedidoDetalle);

        return Optional.of(new PedidoDetalleDTO(savedPedidoDetalle));
    }

    public Optional<PedidoDetalleDTO> updatePedidoDetalle(Integer id, PedidoDetalleDTO pedidoDetalleDTO) {
        return pedidoDetalleRepository.findByIdAndEliminadoFalse(id)
                .map(existingPedidoDetalle -> {
                    existingPedidoDetalle.setCantidad(pedidoDetalleDTO.getCantidad());
                    existingPedidoDetalle.setProducto(new Producto(pedidoDetalleDTO.getProducto()));
                    PedidoDetalle updatedPedidoDetalle = pedidoDetalleRepository.save(existingPedidoDetalle);
                    return new PedidoDetalleDTO(updatedPedidoDetalle);
                });
    }

    public boolean deletePedidoDetalle(Integer id) {
        return pedidoDetalleRepository.findByIdAndEliminadoFalse(id)
                .map(pedidoDetalle -> {
                    pedidoDetalle.setEliminado(true);
                    pedidoDetalleRepository.save(pedidoDetalle);
                    return true;
                }).orElse(false);
    }

    // Recupera todos los detalles de pedido con FK_idPedidoCompra igual a id.
    public List<PedidoDetalle> getDetallesByPedidoCompraId(Integer id) {
        return pedidoDetalleRepository.findByPedidoCompraIdAndEliminadoFalse(id);
    }

    // Recupera todos los precios de los detalles de pedido con FK_idPedidoCompra
    // igual a id.
    public List<PedidoDetallePrecioDTO> getPrecios(Integer id) {
        List<PedidoDetalle> detalles = getDetallesByPedidoCompraId(id);
        if (detalles == null) {
            return null;
        }

        List<Cotizacion> cotizaciones = cotizacionService.getCotizacionesByPedidoCompraId(id);
        if (cotizaciones == null) {
            return null;
        }

        List<CotizacionDetalle> cotizacionesDetalles = new ArrayList<>();
        for (Cotizacion cotizacion : cotizaciones) {
            cotizacionesDetalles.addAll(cotizacionesDetallesService.getDetallesByCotizacionId(cotizacion.getId()));
        }

        List<PedidoDetallePrecioDTO> detallesPrecios = new ArrayList<>();
        for (PedidoDetalle detalle : detalles) {
            Producto producto = detalle.getProducto();
            PedidoDetallePrecioDTO detallePrecio = new PedidoDetallePrecioDTO();
            detallePrecio.setId(detalle.getId());
            List<PrecioDTO> precios = new ArrayList<>();

            for (CotizacionDetalle cotizacionDetalle : cotizacionesDetalles) {
                Producto productoCotizacion = cotizacionDetalle.getProducto();

                if (producto.getId().equals(productoCotizacion.getId())) {
                    PrecioDTO precio = new PrecioDTO();
                    precio.setPrecioUnitario(cotizacionDetalle.getPrecioUnitario());
                    precio.setProveedor(new ProveedorDTO(cotizacionDetalle.getCotizacion().getProveedor()));
                    precios.add(precio);
                }
            }

            detallePrecio.setPrecios(precios);
            detallesPrecios.add(detallePrecio);
        }

        return detallesPrecios;
    }

}
