package modulocompras.api.pedido_compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import modulocompras.api.pedido_compra.detalle.PedidoDetalleDTO;
import modulocompras.api.pedido_compra.detalle.PedidoDetalleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/pedidos-compra") // Endpoint para Pedidos de Compra
@Tag(name = "Pedidos de Compra")
public class PedidoCompraController {

    @Autowired
    private PedidoCompraService pedidoCompraService;

    @Autowired
    private PedidoDetalleService pedidoDetalleService;

    // Obtener todos los pedidos de compra
    @GetMapping
    public List<PedidoCompraDTO> getAllPedidosCompra() {
        return pedidoCompraService.getAllPedidosCompra();
    }

    // Obtener un pedido de compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoCompraDTO> getPedidoCompraById(@PathVariable Integer id) {
        Optional<PedidoCompraDTO> pedidoCompraDTO = pedidoCompraService.getPedidoCompraById(id);
        return pedidoCompraDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo pedido de compra
    @PostMapping
    public ResponseEntity<PedidoCompraDTO> createPedidoCompra(@RequestBody PedidoCompraDTO pedidoCompraDTO) {
        PedidoCompraDTO savedPedidoCompraDTO = pedidoCompraService.createPedidoCompra(pedidoCompraDTO);
        return ResponseEntity.ok(savedPedidoCompraDTO);
    }

    // Actualizar un pedido de compra existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoCompraDTO> updatePedidoCompra(@PathVariable Integer id,
            @RequestBody PedidoCompraDTO pedidoCompraDetails) {
        Optional<PedidoCompraDTO> updatedPedidoCompraDTO = pedidoCompraService.updatePedidoCompra(id,
                pedidoCompraDetails);
        return updatedPedidoCompraDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un pedido de compra
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedidoCompra(@PathVariable Integer id) {
        boolean deleted = pedidoCompraService.deletePedidoCompra(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Recuperar todos los detalles de pedido con FK_idPedidoCompra igual a
    // idPedidoCompra
    @GetMapping("/{idPedidoCompra}/detalles")
    public ResponseEntity<List<PedidoDetalleDTO>> findByDetallesByPedidoCompraId(
            @PathVariable Integer idPedidoCompra) {
        List<PedidoDetalleDTO> detalles = pedidoDetalleService.getDetallesByPedidoCompraId(idPedidoCompra).stream()
                .map(PedidoDetalleDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(detalles);
    }

    // Obtener PedidoDTO del próximo pedido de compra a realizar
    @GetMapping("/preview")
    public PedidoCompraDTO previewPedidoCompra() {
        return pedidoCompraService.previewPedidoCompra();
    }
}
