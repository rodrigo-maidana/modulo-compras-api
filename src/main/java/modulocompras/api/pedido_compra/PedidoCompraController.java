package modulocompras.api.pedido_compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import modulocompras.api.pedido_compra.detalle.PedidoDetalle;
import modulocompras.api.pedido_compra.detalle.PedidoDetalleDTO;
import modulocompras.api.pedido_compra.detalle.PedidoDetalleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidoscompra") // Endpoint para Pedidos de Compra
public class PedidoCompraController {

    @Autowired
    private PedidoCompraRepository pedidoCompraRepository; // Inyección del repositorio de PedidoCompra

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository; // Inyección del repositorio de PedidoDetalle

    // Obtener todos los pedidos de compra
    @GetMapping
    public List<PedidoCompraDTO> getAllPedidosCompra() {
        return pedidoCompraRepository.findByEliminadoFalse().stream()
                .map(pedidoCompra -> new PedidoCompraDTO(pedidoCompra))
                .collect(Collectors.toList());
    }

    // Obtener un pedido de compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoCompraDTO> getPedidoCompraById(@PathVariable Integer id) {
        Optional<PedidoCompra> pedidoCompra = pedidoCompraRepository.findById(id);
        if (pedidoCompra.isPresent() && !pedidoCompra.get().getEliminado()) {
            return ResponseEntity.ok(new PedidoCompraDTO(pedidoCompra.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo pedido de compra
    @PostMapping
    public ResponseEntity<PedidoCompraDTO> createPedidoCompra(@RequestBody PedidoCompraDTO pedidoCompra) {
        PedidoCompra newPedidoCompra = new PedidoCompra();
        newPedidoCompra.setFechaEmision(pedidoCompra.getFechaEmision());
        newPedidoCompra.setEstado(pedidoCompra.getEstado());
        PedidoCompra savedPedidoCompra = pedidoCompraRepository.save(newPedidoCompra);
        return ResponseEntity.ok(new PedidoCompraDTO(savedPedidoCompra));
    }

    // Actualizar un pedido de compra existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoCompraDTO> updatePedidoCompra(@PathVariable Integer id,
            @RequestBody PedidoCompraDTO pedidoCompraDetails) {
        Optional<PedidoCompra> pedidoCompra = pedidoCompraRepository.findById(id);
        if (pedidoCompra.isPresent() && !pedidoCompra.get().getEliminado()) {
            PedidoCompra existingPedidoCompra = pedidoCompra.get();
            existingPedidoCompra.setFechaEmision(pedidoCompraDetails.getFechaEmision());
            existingPedidoCompra.setEstado(pedidoCompraDetails.getEstado());
            PedidoCompra updatedPedidoCompra = pedidoCompraRepository.save(existingPedidoCompra);
            return ResponseEntity.ok(new PedidoCompraDTO(updatedPedidoCompra));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un pedido de compra
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedidoCompra(@PathVariable Integer id) {
        return pedidoCompraRepository.findById(id)
                .map(pedidoCompra -> {
                    pedidoCompra.setEliminado(true);
                    pedidoCompraRepository.save(pedidoCompra);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Recupera todos los detalles de pedido con FK_idPedidoCompra igual a
     * idPedidoCompra.
     * 
     * @param idPedidoCompra El id del pedido compra.
     * @return Una lista de detalles de pedido DTO que coinciden con el
     *         idPedidoCompra dado.
     */
    @GetMapping("/detalles/{id}")
    public ResponseEntity<List<PedidoDetalleDTO>> findByDetallesByPedidoCompraId(
            @PathVariable("id") Integer idPedidoCompra) {
        List<PedidoDetalle> detalles = pedidoDetalleRepository.findByPedidoCompraIdAndEliminadoFalse(idPedidoCompra);
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
