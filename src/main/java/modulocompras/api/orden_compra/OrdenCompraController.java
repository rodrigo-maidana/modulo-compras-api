package modulocompras.api.orden_compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import modulocompras.api.pedido_compra.detalle.PedidoDetalleDTO;
import modulocompras.api.pedido_compra.detalle.PedidoDetallePrecioDTO;
import modulocompras.api.pedido_compra.detalle.PedidoDetalleService;
import modulocompras.api.orden_compra.detalles.OrdenDetalle;
import modulocompras.api.orden_compra.detalles.OrdenDetalleDTO;
import modulocompras.api.orden_compra.detalles.OrdenDetalleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/ordenes-compra") // Endpoint para Ordenes de Compra
@Tag(name = "Ordenes de Compra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraService ordenCompraService;

    @Autowired
    @Lazy
    private OrdenDetalleService ordenDetalleService;

    // Obtener todas las órdenes de compra
    @GetMapping
    public List<OrdenCompraDTO> getAllOrdenesCompra() {
        return ordenCompraService.getAllOrdenesCompra().stream()
                .map(ordenCompra -> new OrdenCompraDTO(ordenCompra))
                .collect(Collectors.toList());
    }

    // Obtener una orden de compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompraDTO> getOrdenCompraById(@PathVariable Integer id) {
        Optional<OrdenCompra> ordenCompra = ordenCompraService.getOrdenCompraById(id);
        return ordenCompra.stream()
                .map(OrdenCompraDTO::new)
                .map(ResponseEntity::ok)
                .findFirst()
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva orden de compra
    @PostMapping
    public ResponseEntity<OrdenCompraDTO> createOrdenCompra(@RequestBody OrdenCompraDTO ordenCompraDTO) {
        OrdenCompra savedOrdenCompra = ordenCompraService.createOrdenCompra(ordenCompraDTO);
        if (savedOrdenCompra != null) {
            return ResponseEntity.ok(new OrdenCompraDTO(savedOrdenCompra));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Actualizar una orden de compra existente
    @PutMapping("/{id}")
    public ResponseEntity<OrdenCompraDTO> updateOrdenCompra(@PathVariable Integer id,
            @RequestBody OrdenCompraDTO ordenCompraDetails) {
        Optional<OrdenCompra> updatedOrdenCompra = ordenCompraService.updateOrdenCompra(id, ordenCompraDetails);
        return updatedOrdenCompra.stream()
                .map(OrdenCompraDTO::new)
                .map(ResponseEntity::ok)
                .findFirst()
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar una orden de compra
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrdenCompra(@PathVariable Integer id) {
        boolean deleted = ordenCompraService.deleteOrdenCompra(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener OrdenCompraDTO de la proxima orden de compra
    @GetMapping("/preview")
    public ResponseEntity<OrdenCompraDTO> getPreviewOrdenCompra() {
        return ResponseEntity.ok(new OrdenCompraDTO(ordenCompraService.previewOrdenCompra()));
    }

    // Obtiene todas las ordentes de compra con FK_idOrdenCompra igual a
    // idOrdenCompra
    @GetMapping("/{idOrdenCompra}/detalles")
    public ResponseEntity<List<OrdenDetalleDTO>> findByDetallesByOrdenCompraId(
            @PathVariable Integer idOrdenCompra) {
        List<OrdenDetalleDTO> detalles = ordenDetalleService.getDetallesByOrdenCompraId(idOrdenCompra).stream()
                .map(OrdenDetalleDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(detalles);
    }
}
