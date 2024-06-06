package modulocompras.api.factura.detalle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/facturas-detalles") // Endpoint para Facturas Detalles
@Tag(name = "Facturas Detalles")
public class FacturaDetalleController {

    @Autowired
    private FacturaDetalleService facturaDetalleService;

    // Obtener todos los facturas detalles
    @GetMapping
    public List<FacturaDetalleDTO> getAllFacturasDetalles() {
        List<FacturaDetalle> facturaDetalles = facturaDetalleService.getAllFacturasDetalles();
        return facturaDetalles.stream()
                .map(FacturaDetalleDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener un factura detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDetalleDTO> getFacturaDetalleById(@PathVariable Integer id) {
        Optional<FacturaDetalle> facturaDetalle = facturaDetalleService.getFacturaDetalleById(id);
        return facturaDetalle
                .map(FacturaDetalleDTO::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo factura detalle
    @PostMapping("/{idFactura}")
    public ResponseEntity<FacturaDetalleDTO> createFacturaDetalle(@PathVariable Integer id,
            @RequestBody FacturaDetalleDTO facturaDetalleDTO) {
        Optional<FacturaDetalle> savedFacturaDetalle = facturaDetalleService.createFacturaDetalle(id,
                facturaDetalleDTO);
        return savedFacturaDetalle
                .map(FacturaDetalleDTO::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
