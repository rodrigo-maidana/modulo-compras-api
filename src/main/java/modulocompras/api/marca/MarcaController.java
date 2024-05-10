package modulocompras.api.marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/marcas") // Endpoint para Marca
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    // Obtener todas las marcas
    @GetMapping
    public List<MarcaDTO> getAllMarcas() {
        return marcaRepository.findByEliminadoFalse().stream()
                .map(marca -> new MarcaDTO(marca))
                .collect(Collectors.toList());
    }

    // Obtener una marca por ID
    @GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> getMarcaById(@PathVariable Integer id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        if (marca.isPresent() && !marca.get().getEliminado()) {
            return ResponseEntity.ok(new MarcaDTO(marca.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva marca
    @PostMapping
    public ResponseEntity<MarcaDTO> createMarca(@RequestBody MarcaDTO marcaDTO) {
        Marca newMarca = new Marca();
        newMarca.setNombre(marcaDTO.getNombre());
        Marca savedMarca = marcaRepository.save(newMarca);
        return ResponseEntity.ok(new MarcaDTO(savedMarca));
    }

    // Actualizar una marca existente
    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> updateMarca(@PathVariable Integer id, @RequestBody MarcaDTO marcaDTO) {
        Optional<Marca> marca = marcaRepository.findById(id);
        if (marca.isPresent() && !marca.get().getEliminado()) {
            Marca existingMarca = marca.get();
            existingMarca.setNombre(marcaDTO.getNombre());
            Marca updatedMarca = marcaRepository.save(existingMarca);
            return ResponseEntity.ok(new MarcaDTO(updatedMarca));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una marca (borrado suave)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMarca(@PathVariable Integer id) {
        return marcaRepository.findById(id)
                .map(marca -> {
                    marca.setEliminado(true);
                    marcaRepository.save(marca);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
