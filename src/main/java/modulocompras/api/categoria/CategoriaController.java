package modulocompras.api.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/categorias") // Endpoint para Categoria
@Tag(name = "Categorías")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Obtener todas las categorías
    @GetMapping
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findByEliminadoFalse().stream()
                .map(categoria -> new CategoriaDTO(categoria))
                .collect(Collectors.toList());
    }

    // Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findByIdAndEliminadoFalse(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(new CategoriaDTO(categoria.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva categoría
    @PostMapping
    public ResponseEntity<CategoriaDTO> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        Categoria newCategoria = new Categoria(categoriaDTO);
        Categoria savedCategoria = categoriaRepository.save(newCategoria);
        return ResponseEntity.ok(new CategoriaDTO(savedCategoria));
    }

    // Actualizar una categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Integer id,
            @RequestBody CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoria = categoriaRepository.findByIdAndEliminadoFalse(id);
        if (categoria.isPresent()) {
            Categoria existingCategoria = categoria.get();
            existingCategoria.setNombre(categoriaDTO.getNombre());
            Categoria updatedCategoria = categoriaRepository.save(existingCategoria);
            return ResponseEntity.ok(new CategoriaDTO(updatedCategoria));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletecategoria(@PathVariable Integer id) {
        return categoriaRepository.findByIdAndEliminadoFalse(id)
                .map(categoria -> {
                    categoria.setEliminado(true);
                    categoriaRepository.save(categoria);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
