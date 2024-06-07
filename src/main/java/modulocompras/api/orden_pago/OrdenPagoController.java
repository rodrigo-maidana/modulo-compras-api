package modulocompras.api.orden_pago;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/orden-pago") // Endpooint para Ordenes de Pago
@Tag(name = "15. Ordenes de Pago")
public class OrdenPagoController {

    @Autowired
    private OrdenPagoService ordenPagoService;

    // Obtener todas las órdenes de pago
    @GetMapping
    public List<OrdenPagoDTO> getAllOrdenesPago() {
        return ordenPagoService.getAllOrdenesPago().stream()
                .map(ordenPago -> new OrdenPagoDTO(ordenPago))
                .collect(Collectors.toList());
    }

    // Obtener una orden de pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenPagoDTO> getOrdenPagoById(@PathVariable Integer id) {
        Optional<OrdenPago> ordenPago = ordenPagoService.getOrdenPagoById(id);
        return ordenPago.stream()
                .map(OrdenPagoDTO::new)
                .map(ResponseEntity::ok)
                .findFirst()
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva orden de pago
    @PostMapping("/{idFactura}")
    public ResponseEntity<OrdenPagoDTO> createOrdenPago(@PathVariable Integer idFactura,
            @RequestBody OrdenPagoDTO ordenPagoDTO) {
        OrdenPago savedOrdenPago = ordenPagoService.createOrdenPago(idFactura, ordenPagoDTO).orElse(null);
        if (savedOrdenPago != null) {
            return ResponseEntity.ok(new OrdenPagoDTO(savedOrdenPago));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Obtener preview de una orden de pago
    @GetMapping("/preview")
    public ResponseEntity<OrdenPagoDTO> getOrdenPagoPreview() {
        OrdenPagoDTO ordenPagoPreview = ordenPagoService.getOrdenPagoPreview();
        return ordenPagoPreview != null
                ? ResponseEntity.ok(ordenPagoPreview)
                : ResponseEntity.badRequest().build();
    }

}
