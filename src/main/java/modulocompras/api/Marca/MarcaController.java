package modulocompras.api.Marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marcas") // Endpoint for Marca
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository; // Rename to MarcaRepository

    // Get all marcas
    @GetMapping
    public List<Marca> getAllMarcas() {
        return marcaRepository.findAll();
    }

    // Get a marca by ID
    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaById(@PathVariable Integer id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        if (marca.isPresent()) {
            return ResponseEntity.ok(marca.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new marca
    @PostMapping
    public Marca createMarca(@RequestBody Marca marca) {
        return marcaRepository.save(marca);
    }

    // Update an existing marca
    @PutMapping("/{id}")
    public ResponseEntity<Marca> updateMarca(@PathVariable Integer id, @RequestBody Marca marcaDetails) {
        return marcaRepository.findById(id)
                .map(marca -> {
                    marca.setNombre(marcaDetails.getNombre()); // Assuming 'nombre' field exists in Marca
                    Marca updatedMarca = marcaRepository.save(marca);
                    return ResponseEntity.ok(updatedMarca);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a marca
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Integer id) {
        return marcaRepository.findById(id)
                .map(marca -> {
                    marca.setEliminado(true);
                    marcaRepository.save(marca);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
