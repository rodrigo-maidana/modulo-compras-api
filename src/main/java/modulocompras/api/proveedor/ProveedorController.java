package modulocompras.api.proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proveedores") // Endpoint para Proveedor
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Obtener todos los proveedores
    @GetMapping
    public List<ProveedorDTO> getAllProveedores() {
        return proveedorRepository.findAll().stream()
                .map(proveedor -> new ProveedorDTO(proveedor))
                .collect(Collectors.toList());
    }

    // Obtener un proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> getProveedorById(@PathVariable Integer id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        if (proveedor.isPresent()) {
            return ResponseEntity.ok(new ProveedorDTO(proveedor.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo proveedor
    @PostMapping
    public ResponseEntity<ProveedorDTO> createProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        Proveedor newProveedor = new Proveedor();
        newProveedor.setNombre(proveedorDTO.getNombre());
        newProveedor.setRuc(proveedorDTO.getRuc());
        newProveedor.setContacto(proveedorDTO.getContacto());
        newProveedor.setCorreo(proveedorDTO.getCorreo());
        newProveedor.setDireccion(proveedorDTO.getDireccion());
        Proveedor savedProveedor = proveedorRepository.save(newProveedor);
        return ResponseEntity.ok(new ProveedorDTO(savedProveedor));
    }

    // Actualizar un proveedor existente
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> updateProveedor(@PathVariable Integer id,
            @RequestBody ProveedorDTO proveedorDTO) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        if (proveedor.isPresent() && !proveedor.get().getEliminado()) {
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
        return proveedorRepository.findById(id)
                .map(proveedor -> {
                    proveedor.setEliminado(true);
                    proveedorRepository.save(proveedor);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
