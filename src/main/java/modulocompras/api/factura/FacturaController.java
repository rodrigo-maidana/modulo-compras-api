package modulocompras.api.factura;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/v1/facturas") // Endpoint para Facturas
@Tag(name = "Facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    // Obtener todas las facturas
    @GetMapping
    public List<FacturaDTO> getAllFacturas() {
        return facturaService.getAllFacturas().stream()
                .map(factura -> new FacturaDTO(factura))
                .collect(Collectors.toList());
    }

    // Obtener una factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> getFacturaById(@RequestParam Integer id) {
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

}
