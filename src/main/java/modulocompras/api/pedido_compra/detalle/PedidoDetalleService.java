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

    private PedidoDetalleRepository pedidoDetalleRepository;

    @Autowired
    private PedidoCompraService pedidoCompraService;

    @Autowired
    private ProductoService productoService;

    public PedidoDetalleService(PedidoDetalleRepository pedidoDetalleRepository) {
        this.pedidoDetalleRepository = pedidoDetalleRepository;
    }

    public List<PedidoDetalleDTO> getAllPedidosDetalles() {
        return pedidoDetalleRepository.findByEliminadoFalse().stream()
                .map(PedidoDetalleDTO::new)
                .collect(Collectors.toList());
    }

    public ResponseEntity<PedidoDetalleDTO> getPedidoDetalleById(Integer id) {
        Optional<PedidoDetalle> pedidoDetalle = pedidoDetalleRepository.findByIdAndEliminadoFalse(id);
        if (pedidoDetalle.isPresent()) {
            return ResponseEntity.ok(new PedidoDetalleDTO(pedidoDetalle.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<PedidoDetalleDTO> createPedidoDetalle(Integer id, PedidoDetalleDTO pedidoDetalleDTO) {
        Optional<Producto> optionalProducto = productoService.getProductoByDTO(pedidoDetalleDTO.getProducto());
        if (!optionalProducto.isPresent()) {
            return ResponseEntity.badRequest().body(null); // O un mensaje de error adecuado
        }

        Optional<PedidoCompra> optionalPedidoCompra = pedidoCompraService.findByIdAndEliminadoFalse(id);
        if (!optionalPedidoCompra.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Producto producto = optionalProducto.get();
        PedidoCompra pedidoCompra = optionalPedidoCompra.get();

        PedidoDetalle newPedidoDetalle = new PedidoDetalle(pedidoDetalleDTO);
        newPedidoDetalle.setProducto(producto);
        newPedidoDetalle.setPedidoCompra(pedidoCompra);

        PedidoDetalle savedPedidoDetalle = pedidoDetalleRepository.save(newPedidoDetalle);

        return ResponseEntity.ok(new PedidoDetalleDTO(savedPedidoDetalle));
    }

    public ResponseEntity<PedidoDetalleDTO> updatePedidoDetalle(Integer id, PedidoDetalleDTO pedidoDetalleDTO) {
        Optional<PedidoDetalle> pedidoDetalle = pedidoDetalleRepository.findByIdAndEliminadoFalse(id);
        if (pedidoDetalle.isPresent()) {
            PedidoDetalle existingPedidoDetalle = pedidoDetalle.get();
            existingPedidoDetalle.setCantidad(pedidoDetalleDTO.getCantidad());
            existingPedidoDetalle.setProducto(new Producto(pedidoDetalleDTO.getProducto()));
            PedidoDetalle updatedPedidoDetalle = pedidoDetalleRepository.save(existingPedidoDetalle);

            return ResponseEntity.ok(new PedidoDetalleDTO(updatedPedidoDetalle));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Object> deletePedidoDetalle(Integer id) {
        return pedidoDetalleRepository.findByIdAndEliminadoFalse(id)
                .map(pedidoDetalle -> {
                    pedidoDetalle.setEliminado(true);
                    pedidoDetalleRepository.save(pedidoDetalle);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Recupera todos los detalles de pedido con FK_idPedidoCompra igual a id.
    public ResponseEntity<List<PedidoDetalleDTO>> findByDetallesByPedidoCompraId(Integer id) {
        List<PedidoDetalle> detalles = pedidoDetalleRepository.findByPedidoCompraIdAndEliminadoFalse(id);
        if (detalles.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentran detalles
        }
        List<PedidoDetalleDTO> detallesDTO = detalles.stream()
                .map(pedidoDetalle -> new PedidoDetalleDTO(pedidoDetalle)) // Convierte cada PedidoDetalle a
                                                                           // PedidoDetalleDTO
                .collect(Collectors.toList());
        return ResponseEntity.ok(detallesDTO); // Retorna los detalles encontrados
    }

}
