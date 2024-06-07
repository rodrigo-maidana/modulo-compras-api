package modulocompras.api.marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/marcas") // Endpoint para Marca
@Tag(name = "4. Marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    // Obtener todas las marcas
    @GetMapping
    public List<MarcaDTO> getAllMarcas() {
        return marcaService.getAllMarcas();
    }

    // Obtener una marca por ID
    @GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> getMarcaById(@PathVariable Integer id) {
        Optional<MarcaDTO> marcaDTO = marcaService.getMarcaById(id);
        if (marcaDTO.isPresent()) {
            return ResponseEntity.ok(marcaDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva marca
    @PostMapping
    public ResponseEntity<MarcaDTO> createMarca(@RequestBody MarcaDTO marcaDTO) {
        MarcaDTO savedMarcaDTO = marcaService.createMarca(marcaDTO);
        return ResponseEntity.ok(savedMarcaDTO);
    }

    // Actualizar una marca existente
    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> updateMarca(@PathVariable Integer id, @RequestBody MarcaDTO marcaDTO) {
        Optional<MarcaDTO> updatedMarcaDTO = marcaService.updateMarca(id, marcaDTO);
        if (updatedMarcaDTO.isPresent()) {
            return ResponseEntity.ok(updatedMarcaDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una marca (borrado suave)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMarca(@PathVariable Integer id) {
        boolean deleted = marcaService.deleteMarca(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
