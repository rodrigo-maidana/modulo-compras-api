package modulocompras.api.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import modulocompras.api.proveedor.ProveedorDTO;
import modulocompras.api.proveedor_categoria.ProveedorCategoriaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/categorias") // Endpoint para Categoria
@Tag(name = "Categorías")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProveedorCategoriaService proveedorCategoriaService;

    // Obtener todas las categorías
    @GetMapping
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }

    // Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Integer id) {
        Optional<CategoriaDTO> categoriaDTO = categoriaService.getCategoriaById(id);
        if (categoriaDTO.isPresent()) {
            return ResponseEntity.ok(categoriaDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva categoría
    @PostMapping
    public ResponseEntity<CategoriaDTO> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO savedCategoriaDTO = categoriaService.createCategoria(categoriaDTO);
        return ResponseEntity.ok(savedCategoriaDTO);
    }

    // Actualizar una categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Integer id,
            @RequestBody CategoriaDTO categoriaDTO) {
        Optional<CategoriaDTO> updatedCategoriaDTO = categoriaService.updateCategoria(id, categoriaDTO);
        if (updatedCategoriaDTO.isPresent()) {
            return ResponseEntity.ok(updatedCategoriaDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategoria(@PathVariable Integer id) {
        boolean deleted = categoriaService.deleteCategoria(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Listar todos los proveedores de una categoría
    @GetMapping("/{idCategoria}/proveedores")
    public List<ProveedorDTO> getProveedoresFromCategoria(@PathVariable Integer idCategoria) {
        return proveedorCategoriaService.getProveedores(idCategoria);
    }
}
