package modulocompras.api.PedidoCompra.PedidoDetalle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidosdetalles") // Endpoint para Pedidos Detalles
public class PedidoDetalleController {

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository; // Inyección del repositorio de PedidoDetalle

    // Obtener todos los pedidos detalles
    @GetMapping
    public List<PedidoDetalle> getAllPedidosDetalles() {
        return pedidoDetalleRepository.findAll();
    }

    // Obtener un pedido detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalle> getPedidoDetalleById(@PathVariable Integer id) {
        Optional<PedidoDetalle> pedidoDetalle = pedidoDetalleRepository.findById(id);
        if (pedidoDetalle.isPresent()) {
            return ResponseEntity.ok(pedidoDetalle.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo pedido detalle
    @PostMapping
    public PedidoDetalle createPedidoDetalle(@RequestBody PedidoDetalle pedidoDetalle) {
        return pedidoDetalleRepository.save(pedidoDetalle);
    }

    // Actualizar un pedido detalle existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDetalle> updatePedidoDetalle(@PathVariable Integer id, @RequestBody PedidoDetalle pedidoDetalleDetails) {
        return pedidoDetalleRepository.findById(id)
                .map(pedidoDetalle -> {
                    pedidoDetalle.setCantidad(pedidoDetalleDetails.getCantidad());
                    pedidoDetalle.setProducto(pedidoDetalleDetails.getProducto());
                    PedidoDetalle updatedPedidoDetalle = pedidoDetalleRepository.save(pedidoDetalle);
                    return ResponseEntity.ok(updatedPedidoDetalle);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un pedido detalle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoDetalle(@PathVariable Integer id) {
        return pedidoDetalleRepository.findById(id)
                .map(pedidoDetalle -> {
                    pedidoDetalleRepository.delete(pedidoDetalle);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
