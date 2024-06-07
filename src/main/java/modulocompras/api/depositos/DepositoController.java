package modulocompras.api.depositos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/depositos") // Endpoint para Deposito
@Tag(name = "E. Depósitos")
public class DepositoController {

    @Autowired
    private DepositoService depositoService;

    // Obtener todos los depositos
    @GetMapping
    public List<DepositoDTO> getAllDepositos() {
        return depositoService.getAllDepositos();
    }

    // Obtener un deposito por ID
    @GetMapping("/{id}")
    public ResponseEntity<DepositoDTO> getDepositoById(@PathVariable Integer id) {
        Optional<Deposito> deposito = depositoService.getDepositoById(id);
        return deposito
                .map(value -> ResponseEntity.ok(new DepositoDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo deposito
    @PostMapping
    public ResponseEntity<DepositoDTO> createDeposito(@RequestBody DepositoDTO depositoDTO) {
        DepositoDTO savedDepositoDTO = depositoService.createDeposito(depositoDTO);
        return ResponseEntity.ok(savedDepositoDTO);
    }

    // Actualizar un deposito existente
    @PutMapping("/{id}")
    public ResponseEntity<DepositoDTO> updateDeposito(@PathVariable Integer id, @RequestBody DepositoDTO depositoDTO) {
        Optional<DepositoDTO> updatedDepositoDTO = depositoService.updateDeposito(id, depositoDTO);
        if (updatedDepositoDTO.isPresent()) {
            return ResponseEntity.ok(updatedDepositoDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un deposito
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDeposito(@PathVariable Integer id) {
        boolean deleted = depositoService.deleteDeposito(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
