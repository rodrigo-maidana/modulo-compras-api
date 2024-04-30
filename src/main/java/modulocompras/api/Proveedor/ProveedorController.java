package modulocompras.api.Proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores") // Endpoint for Proveedor
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository; // Rename to ProveedorRepository

    // Get all proveedores
    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    // Get a proveedor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable Integer id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        if (proveedor.isPresent()) {
            return ResponseEntity.ok(proveedor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new proveedor
    @PostMapping
    public Proveedor createProveedor(@RequestBody Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    // Update an existing proveedor
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedorDetails) {
        return proveedorRepository.findById(id)
                .map(proveedor -> {
                    proveedor.setNombre(proveedorDetails.getNombre()); // Assuming 'nombre' field exists in Proveedor
                    Proveedor updatedProveedor = proveedorRepository.save(proveedor);
                    return ResponseEntity.ok(updatedProveedor);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Integer id) {
        return proveedorRepository.findById(id)
                .map(proveedor -> {
                    proveedorRepository.delete(proveedor);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}