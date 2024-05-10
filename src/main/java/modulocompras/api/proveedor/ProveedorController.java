package modulocompras.api.proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProveedorDTO(savedProveedor));
    }

    // Actualizar un proveedor existente
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> updateProveedor(@PathVariable Integer id,
            @RequestBody ProveedorDTO proveedorDTO) {
        return proveedorRepository.findById(id)
                .map(proveedor -> {
                    proveedor.setNombre(proveedorDTO.getNombre());
                    proveedor.setRuc(proveedorDTO.getRuc());
                    proveedor.setContacto(proveedorDTO.getContacto());
                    proveedor.setCorreo(proveedorDTO.getCorreo());
                    proveedor.setDireccion(proveedorDTO.getDireccion());
                    Proveedor updatedProveedor = proveedorRepository.save(proveedor);
                    return ResponseEntity.ok(new ProveedorDTO(updatedProveedor));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Borrar un proveedor (borrado suave)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Integer id) {
        return proveedorRepository.findById(id)
                .map(proveedor -> {
                    proveedor.setEliminado(true);
                    proveedorRepository.save(proveedor);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
