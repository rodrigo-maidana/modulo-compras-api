package modulocompras.api.pedido_compra.detalle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import modulocompras.api.pedido_compra.PedidoCompra;
import modulocompras.api.pedido_compra.PedidoCompraService;
import modulocompras.api.producto.Producto;
import modulocompras.api.producto.ProductoService;

@Service
public class PedidoDetalleService {

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;

    @Autowired
    private PedidoCompraService pedidoCompraService;

    @Autowired
    private ProductoService productoService;

    public List<PedidoDetalleDTO> getAllPedidosDetalles() {
        return pedidoDetalleRepository.findByEliminadoFalse().stream()
                .map(PedidoDetalleDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<PedidoDetalleDTO> getPedidoDetalleById(Integer id) {
        return pedidoDetalleRepository.findByIdAndEliminadoFalse(id)
                .map(PedidoDetalleDTO::new);
    }

    public Optional<PedidoDetalleDTO> createPedidoDetalle(Integer id, PedidoDetalleDTO pedidoDetalleDTO) {
        Optional<Producto> optionalProducto = productoService.getProductoByDTO(pedidoDetalleDTO.getProducto());
        if (!optionalProducto.isPresent()) {
            return Optional.empty();
        }

        Optional<PedidoCompra> optionalPedidoCompra = pedidoCompraService.findByIdAndEliminadoFalse(id);
        if (!optionalPedidoCompra.isPresent()) {
            return Optional.empty();
        }

        Producto producto = optionalProducto.get();
        PedidoCompra pedidoCompra = optionalPedidoCompra.get();
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
    public List<PedidoDetalleDTO> findByDetallesByPedidoCompraId(Integer id) {
        return pedidoDetalleRepository.findByPedidoCompraIdAndEliminadoFalse(id).stream()
                .map(PedidoDetalleDTO::new)
                .collect(Collectors.toList());
    }
}
