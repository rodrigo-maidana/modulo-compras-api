package modulocompras.api.pedido_compra.detalle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/pedidos-detalles") // Endpoint para Pedidos Detalles
@Tag(name = "G. Pedidos Detalles")
public class PedidoDetalleController {

    @Autowired
    private PedidoDetalleService pedidoDetalleService;

    // Obtener todos los pedidos detalles
    @GetMapping
    public List<PedidoDetalleDTO> getAllPedidosDetalles() {
        List<PedidoDetalle> pedidoDetalles = pedidoDetalleService.getAllPedidosDetalles();
        return pedidoDetalles.stream()
                .map(PedidoDetalleDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener un pedido detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalleDTO> getPedidoDetalleById(@PathVariable Integer id) {
        Optional<PedidoDetalleDTO> pedidoDetalleDTO = pedidoDetalleService.getPedidoDetalleById(id);
        return pedidoDetalleDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo pedido detalle
    @PostMapping("/{id}")
    public ResponseEntity<PedidoDetalleDTO> createPedidoDetalle(@PathVariable Integer id,
            @RequestBody PedidoDetalleDTO pedidoDetalleDTO) {
        Optional<PedidoDetalleDTO> savedPedidoDetalleDTO = pedidoDetalleService.createPedidoDetalle(id,
                pedidoDetalleDTO);
        return savedPedidoDetalleDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    // Actualizar un pedido detalle existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDetalleDTO> updatePedidoDetalle(@PathVariable Integer id,
            @RequestBody PedidoDetalleDTO pedidoDetalleDTO) {
        Optional<PedidoDetalleDTO> updatedPedidoDetalleDTO = pedidoDetalleService.updatePedidoDetalle(id,
                pedidoDetalleDTO);
        return updatedPedidoDetalleDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un pedidoDetalle (borrado suave)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedidoDetalle(@PathVariable Integer id) {
        boolean deleted = pedidoDetalleService.deletePedidoDetalle(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
