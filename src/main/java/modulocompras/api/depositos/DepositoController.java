package modulocompras.api.depositos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/depositos") // Endpoint para Deposito
@Tag(name = "Depósitos")
public class DepositoController {

    @Autowired
    private DepositoRepository depositoRepository;

    // Obtener todos los depositos
    @GetMapping
    public List<DepositoDTO> getAllDepositos() {
        return depositoRepository.findByEliminadoFalse().stream()
                .map(deposito -> new DepositoDTO(deposito))
                .collect(Collectors.toList());
    }

    // Obtener un deposito por ID
    @GetMapping("/{id}")
    public ResponseEntity<DepositoDTO> getDepositoById(@PathVariable Integer id) {
        Optional<Deposito> deposito = depositoRepository.findById(id);
        if (deposito.isPresent() && !deposito.get().getEliminado()) {
            return ResponseEntity.ok(new DepositoDTO(deposito.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo deposito
    @PostMapping
    public ResponseEntity<DepositoDTO> createDeposito(@RequestBody DepositoDTO depositoDTO) {
        Deposito newDeposito = new Deposito();
        newDeposito.setNombre(depositoDTO.getNombre());
        newDeposito.setDireccion(depositoDTO.getDireccion());
        newDeposito.setContacto(depositoDTO.getContacto());
        Deposito savedDeposito = depositoRepository.save(newDeposito);
        return ResponseEntity.ok(new DepositoDTO(savedDeposito));
    }

    // Actualizar un deposito existente
    @PutMapping("/{id}")
    public ResponseEntity<DepositoDTO> updateDeposito(@PathVariable Integer id, @RequestBody DepositoDTO depositoDTO) {
        Optional<Deposito> deposito = depositoRepository.findById(id);
        if (deposito.isPresent() && !deposito.get().getEliminado()) {
            Deposito existingDeposito = deposito.get();
            existingDeposito.setNombre(depositoDTO.getNombre());
            existingDeposito.setDireccion(depositoDTO.getDireccion());
            existingDeposito.setContacto(depositoDTO.getContacto());
            Deposito updatedDeposito = depositoRepository.save(existingDeposito);
            return ResponseEntity.ok(new DepositoDTO(updatedDeposito));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un deposito
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDeposito(@PathVariable Integer id) {
        return depositoRepository.findById(id)
                .map(deposito -> {
                    deposito.setEliminado(true);
                    depositoRepository.save(deposito);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
