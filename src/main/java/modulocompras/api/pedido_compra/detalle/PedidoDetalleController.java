package modulocompras.api.pedido_compra.detalle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import modulocompras.api.producto.Producto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidosdetalles") // Endpoint para Pedidos Detalles
public class PedidoDetalleController {

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;

    // Obtener todos los pedidos detalles
    @GetMapping
    public List<PedidoDetalleDTO> getAllPedidosDetalles() {
        return pedidoDetalleRepository.findByEliminadoFalse().stream()
                .map(PedidoDetalleDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener un pedido detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalleDTO> getPedidoDetalleById(@PathVariable Integer id) {
        Optional<PedidoDetalle> pedidoDetalle = pedidoDetalleRepository.findByIdAndEliminadoFalse(id);
        if (pedidoDetalle.isPresent()) {
            return ResponseEntity.ok(new PedidoDetalleDTO(pedidoDetalle.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo pedido detalle
    @PostMapping
    public ResponseEntity<PedidoDetalleDTO> createPedidoDetalle(@RequestBody PedidoDetalleDTO pedidoDetalleDTO) {
        PedidoDetalle newPedidoDetalle = new PedidoDetalle(pedidoDetalleDTO);
        PedidoDetalle savedPedidoDetalle = pedidoDetalleRepository.save(newPedidoDetalle);
        return ResponseEntity.ok(new PedidoDetalleDTO(savedPedidoDetalle));
    }

    // Actualizar un pedido detalle existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDetalleDTO> updatePedidoDetalle(@PathVariable Integer id,
            @RequestBody PedidoDetalleDTO pedidoDetalleDTO) {
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

    // Eliminar un pedidoDetalle (borrado suave)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedidoDetalle(@PathVariable Integer id) {
        return pedidoDetalleRepository.findByIdAndEliminadoFalse(id)
                .map(pedidoDetalle -> {
                    pedidoDetalle.setEliminado(true);
                    pedidoDetalleRepository.save(pedidoDetalle);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
