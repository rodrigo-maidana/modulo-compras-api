package modulocompras.api.pedido_compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import modulocompras.api.pedido_compra.detalle.PedidoDetalleDTO;
import modulocompras.api.pedido_compra.detalle.PedidoDetalleService;

import java.util.List;

@RestController
@RequestMapping("/pedidoscompra") // Endpoint para Pedidos de Compra
@Tag(name = "Pedidos de Compra")
public class PedidoCompraController {

    @Autowired
    private PedidoCompraService pedidoCompraService; // Inyección del servicio de PedidoCompra

    @Autowired
    private PedidoDetalleService pedidoDetalleService; // Inyección del servicio de PedidoDetalle

    // Obtener todos los pedidos de compra
    @GetMapping
    public List<PedidoCompraDTO> getAllPedidosCompra() {
        return pedidoCompraService.getAllPedidosCompra();
    }

    // Obtener un pedido de compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoCompraDTO> getPedidoCompraById(@PathVariable Integer id) {
        return pedidoCompraService.getPedidoCompraById(id);
    }

    // Crear un nuevo pedido de compra
    @PostMapping
    public ResponseEntity<PedidoCompraDTO> createPedidoCompra(@RequestBody PedidoCompraDTO pedidoCompraDTO) {
        PedidoCompra newPedidoCompra = pedidoCompraService.createPedidoCompra(pedidoCompraDTO);
        return ResponseEntity.ok(new PedidoCompraDTO(newPedidoCompra));
    }

    // Actualizar un pedido de compra existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoCompraDTO> updatePedidoCompra(@PathVariable Integer id,
            @RequestBody PedidoCompraDTO pedidoCompraDetails) {
        return pedidoCompraService.updatePedidoCompra(id, pedidoCompraDetails);
    }

    // Eliminar un pedido de compra
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedidoCompra(@PathVariable Integer id) {
        return pedidoCompraService.softDeletePedidoCompra(id);
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
        return pedidoDetalleService.findByDetallesByPedidoCompraId(idPedidoCompra);
    }

    // Obtener PedidoDTO de el proximo pedido de compra a realizar
    @GetMapping("/preview")
    public PedidoCompraDTO previewPedidoCompra() {
        return pedidoCompraService.previewPedidoCompra();
    }
}
