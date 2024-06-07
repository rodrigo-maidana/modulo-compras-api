package modulocompras.api.factura;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import modulocompras.api.factura.detalle.FacturaDetalleDTO;
import modulocompras.api.factura.detalle.FacturaDetalleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/v1/facturas") // Endpoint para Facturas
@Tag(name = "Facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private FacturaDetalleService facturaDetalleService;

    // Obtener todas las facturas
    @GetMapping
    public List<FacturaDTO> getAllFacturas() {
        return facturaService.getAllFacturas().stream()
                .map(factura -> new FacturaDTO(factura))
                .collect(Collectors.toList());
    }

    // Obtener una factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> getFacturaById(@PathVariable Integer id) {
        Optional<Factura> factura = facturaService.getFacturaById(id);
        return factura.stream()
                .map(FacturaDTO::new)
                .map(ResponseEntity::ok)
                .findFirst()
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar una factura existente
    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO> updateFactura(@PathVariable Integer id,
            @RequestBody FacturaDTO facturaDTO) {
        Optional<Factura> updatedFactura = facturaService.updateFactura(id, facturaDTO);
        return updatedFactura.map(FacturaDTO::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Crear una nueva factura
    @PostMapping()
    public ResponseEntity<FacturaDTO> createFactura(@RequestBody FacturaCreateDTO facturaCreateDTO) {
        Optional<Factura> createdFactura = facturaService.createFactura(facturaCreateDTO);
        return createdFactura.map(FacturaDTO::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Eliminar una factura
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFactura(@PathVariable Integer id) {
        boolean deleted = facturaService.deletedFactura(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Obtener todos los detalles de una factura
    @GetMapping("/{idFactura}/detalles")
    public ResponseEntity<List<FacturaDetalleDTO>> getDetallesByFacturaId(@PathVariable Integer idFactura) {
        List<FacturaDetalleDTO> detalles = facturaDetalleService.getAllFacturasDetalles().stream()
                .filter(facturaDetalle -> facturaDetalle.getFactura().getId().equals(idFactura))
                .map(FacturaDetalleDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(detalles);
    }

}
