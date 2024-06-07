package modulocompras.api.metodo_pago;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/metodos-pago")
@Tag(name = "N. Métodos de pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    // Obtener todos los métodos de pago
    @GetMapping
    public List<MetodoPagoDTO> getAllMetodosPago() {
        return metodoPagoService.getAllMetodosPago().stream()
                .map(MetodoPagoDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener un método de pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> getMetodoPagoById(@PathVariable Integer id) {
        return metodoPagoService.getMetodoPagoById(id)
                .map(MetodoPagoDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un método de pago
    @PostMapping
    public ResponseEntity<MetodoPagoDTO> createMetodoPago(@RequestBody MetodoPagoDTO metodoPagoDTO) {
        MetodoPago newMetodoPago = metodoPagoService.createMetodoPago(metodoPagoDTO).orElse(null);
        return newMetodoPago != null
                ? ResponseEntity.ok(new MetodoPagoDTO(newMetodoPago))
                : ResponseEntity.badRequest().build();
    }
}
