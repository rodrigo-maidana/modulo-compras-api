package modulocompras.api.proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import modulocompras.api.categoria.CategoriaDTO;
import modulocompras.api.proveedor_categoria.ProveedorCategoriaDTO;
import modulocompras.api.proveedor_categoria.ProveedorCategoriaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/proveedores") // Endpoint para Proveedor
@Tag(name = "Proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProveedorCategoriaService proveedorCategoriaService;

    // Obtener todos los proveedores
    @GetMapping
    public List<ProveedorDTO> getAllProveedores() {
        return proveedorService.getAllProveedores();
    }

    // Obtener un proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> getProveedorById(@PathVariable Integer id) {
        Optional<ProveedorDTO> proveedorDTO = proveedorService.getProveedorById(id)
                .map(ProveedorDTO::new);
        return proveedorDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo proveedor
    @PostMapping
    public ResponseEntity<ProveedorDTO> createProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        ProveedorDTO savedProveedorDTO = proveedorService.createProveedor(proveedorDTO);
        return ResponseEntity.ok(savedProveedorDTO);
    }

    // Actualizar un proveedor existente
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> updateProveedor(@PathVariable Integer id,
            @RequestBody ProveedorDTO proveedorDTO) {
        Optional<ProveedorDTO> updatedProveedorDTO = proveedorService.updateProveedor(id, proveedorDTO);
        return updatedProveedorDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Borrar un proveedor (borrado suave)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProveedor(@PathVariable Integer id) {
        boolean deleted = proveedorService.deleteProveedor(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Agregar una categoría a un proveedor
    @PostMapping("/categorias")
    public ResponseEntity<ProveedorCategoriaDTO> addCategoriaToProveedor(
            @RequestBody ProveedorCategoriaDTO proveedorCategoriaDTO) {
        ProveedorCategoriaDTO proveedorCategoria = proveedorCategoriaService
                .addCategoriaToProveedor(proveedorCategoriaDTO);
        return ResponseEntity.ok(proveedorCategoria);
    }

    // Listar todas las categorías de un proveedor
    @GetMapping("/{idProveedor}/categorias")
    public List<CategoriaDTO> getCategoriasFromProveedor(@PathVariable Integer idProveedor) {
        return proveedorCategoriaService.getCategorias(idProveedor);
    }
}
