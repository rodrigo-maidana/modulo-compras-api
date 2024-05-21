package modulocompras.api.pedido_compra.detalle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/pedidosdetalles") // Endpoint para Pedidos Detalles
@Tag(name = "Pedidos Detalles")
public class PedidoDetalleController {

    @Autowired
    private PedidoDetalleService pedidoDetalleService; // Inyección del servicio de PedidoDetalle

    // Obtener todos los pedidos detalles
    @GetMapping
    public List<PedidoDetalleDTO> getAllPedidosDetalles() {
        return pedidoDetalleService.getAllPedidosDetalles();
    }

    // Obtener un pedido detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalleDTO> getPedidoDetalleById(@PathVariable Integer id) {
        return pedidoDetalleService.getPedidoDetalleById(id);
    }

    // Crear un nuevo pedido detalle
    @PostMapping
    public ResponseEntity<PedidoDetalleDTO> createPedidoDetalle(@RequestBody PedidoDetalleDTO pedidoDetalleDTO) {
        return pedidoDetalleService.createPedidoDetalle(pedidoDetalleDTO);
    }

    // Actualizar un pedido detalle existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDetalleDTO> updatePedidoDetalle(@PathVariable Integer id,
            @RequestBody PedidoDetalleDTO pedidoDetalleDTO) {
        return pedidoDetalleService.updatePedidoDetalle(id, pedidoDetalleDTO);
    }

    // Eliminar un pedidoDetalle (borrado suave)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedidoDetalle(@PathVariable Integer id) {
        return pedidoDetalleService.deletePedidoDetalle(id);
    }

}
