package modulocompras.api.PedidoCompra.PedidoCompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidoscompra") // Endpoint para Pedidos de Compra
public class PedidoCompraController {

    @Autowired
    private PedidoCompraRepository pedidoCompraRepository; // Inyección del repositorio de PedidoCompra

    // Obtener todos los pedidos de compra
    @GetMapping
    public List<PedidoCompra> getAllPedidosCompra() {
        return pedidoCompraRepository.findAll();
    }

    // Obtener un pedido de compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoCompra> getPedidoCompraById(@PathVariable Integer id) {
        Optional<PedidoCompra> pedidoCompra = pedidoCompraRepository.findById(id);
        if (pedidoCompra.isPresent()) {
            return ResponseEntity.ok(pedidoCompra.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo pedido de compra
    @PostMapping
    public PedidoCompra createPedidoCompra(@RequestBody PedidoCompra pedidoCompra) {
        return pedidoCompraRepository.save(pedidoCompra);
    }

    // Actualizar un pedido de compra existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoCompra> updatePedidoCompra(@PathVariable Integer id, @RequestBody PedidoCompra pedidoCompraDetails) {
        return pedidoCompraRepository.findById(id)
                .map(pedidoCompra -> {
                    pedidoCompra.setFechaEmision(pedidoCompraDetails.getFechaEmision());
                    pedidoCompra.setEstado(pedidoCompra.getEstado());
                    PedidoCompra updatedPedidoCompra = pedidoCompraRepository.save(pedidoCompra);
                    return ResponseEntity.ok(updatedPedidoCompra);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un pedido de compra
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoCompra(@PathVariable Integer id) {
        return pedidoCompraRepository.findById(id)
                .map(pedidoCompra -> {
                    pedidoCompraRepository.delete(pedidoCompra);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
