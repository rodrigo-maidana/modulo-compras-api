package modulocompras.api.Deposito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/depositos")
public class DepositoController {

    @Autowired
    private DepositoRepository depositoRepository;

    // Obtener todos los depositos
    @GetMapping
    public List<Deposito> getAllDepositos() {
        return depositoRepository.findAll();
    }

    // Obtener un deposito por ID
    @GetMapping("/{id}")
    public ResponseEntity<Deposito> getDepositoById(@PathVariable Integer id) {
        Optional<Deposito> deposito = depositoRepository.findById(id);
        if (deposito.isPresent()) {
            return ResponseEntity.ok(deposito.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva categoría
    @PostMapping
    public Deposito createDeposito(@RequestBody Deposito deposito) {
        return depositoRepository.save(deposito);
    }

    // Actualizar un deposito existente
    @PutMapping("/{id}")
    public ResponseEntity<Deposito> updateDeposito(@PathVariable Integer id, @RequestBody Deposito depositoDetails) {
        return depositoRepository.findById(id)
                .map(deposito -> {
                    deposito.setNombre(depositoDetails.getNombre());
                    deposito.setDireccion(depositoDetails.getDireccion());
                    deposito.setContacto(depositoDetails.getContacto());
                    Deposito updatedDeposito = depositoRepository.save(deposito);
                    return ResponseEntity.ok(updatedDeposito);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un deposito
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeposito(@PathVariable Integer id) {
        return depositoRepository.findById(id)
                .map(deposito -> {
                    deposito.setEliminado(true);
                    depositoRepository.save(deposito);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
