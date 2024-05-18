package modulocompras.api.proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proveedores") // Endpoint para Proveedor
@Tag(name = "Proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Obtener todos los proveedores
    @GetMapping
    public List<ProveedorDTO> getAllProveedores() {
        return proveedorRepository.findByEliminadoFalse().stream()
                .map(proveedor -> new ProveedorDTO(proveedor))
                .collect(Collectors.toList());
    }

    // Obtener un proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> getProveedorById(@PathVariable Integer id) {
        Optional<Proveedor> proveedor = proveedorRepository.findByIdAndEliminadoFalse(id);
        if (proveedor.isPresent()) {
            return ResponseEntity.ok(new ProveedorDTO(proveedor.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo proveedor
    @PostMapping
    public ResponseEntity<ProveedorDTO> createProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        Proveedor newProveedor = new Proveedor(proveedorDTO);
        Proveedor savedProveedor = proveedorRepository.save(newProveedor);
        return ResponseEntity.ok(new ProveedorDTO(savedProveedor));
    }

    // Actualizar un proveedor existente
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> updateProveedor(@PathVariable Integer id,
            @RequestBody ProveedorDTO proveedorDTO) {
        Optional<Proveedor> proveedor = proveedorRepository.findByIdAndEliminadoFalse(id);
        if (proveedor.isPresent()) {
            Proveedor existingProveedor = proveedor.get();
            existingProveedor.setNombre(proveedorDTO.getNombre());
            existingProveedor.setRuc(proveedorDTO.getRuc());
            existingProveedor.setContacto(proveedorDTO.getContacto());
            existingProveedor.setCorreo(proveedorDTO.getCorreo());
            existingProveedor.setDireccion(proveedorDTO.getDireccion());
            Proveedor updatedProveedor = proveedorRepository.save(existingProveedor);
            return ResponseEntity.ok(new ProveedorDTO(updatedProveedor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Borrar un proveedor (borrado suave)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProveedor(@PathVariable Integer id) {
        return proveedorRepository.findByIdAndEliminadoFalse(id)
                .map(proveedor -> {
                    proveedor.setEliminado(true);
                    proveedorRepository.save(proveedor);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
