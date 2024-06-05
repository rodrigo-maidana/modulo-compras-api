package modulocompras.api.proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<ProveedorDTO> getAllProveedores() {
        return proveedorRepository.findByEliminadoFalse().stream()
                .map(ProveedorDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<Proveedor> getProveedorById(Integer id) {
        return proveedorRepository.findByIdAndEliminadoFalse(id);
    }

    public ProveedorDTO createProveedor(ProveedorDTO proveedorDTO) {
        Proveedor newProveedor = new Proveedor(proveedorDTO);
        Proveedor savedProveedor = proveedorRepository.save(newProveedor);
        return new ProveedorDTO(savedProveedor);
    }

    public Optional<ProveedorDTO> updateProveedor(Integer id, ProveedorDTO proveedorDTO) {
        return proveedorRepository.findByIdAndEliminadoFalse(id)
                .map(existingProveedor -> {
                    existingProveedor.setNombre(proveedorDTO.getNombre());
                    existingProveedor.setRuc(proveedorDTO.getRuc());
                    existingProveedor.setContacto(proveedorDTO.getContacto());
                    existingProveedor.setCorreo(proveedorDTO.getCorreo());
                    existingProveedor.setDireccion(proveedorDTO.getDireccion());
                    Proveedor updatedProveedor = proveedorRepository.save(existingProveedor);
                    return new ProveedorDTO(updatedProveedor);
                });
    }

    public boolean deleteProveedor(Integer id) {
        return proveedorRepository.findByIdAndEliminadoFalse(id)
                .map(proveedor -> {
                    proveedor.setEliminado(true);
                    proveedorRepository.save(proveedor);
                    return true;
                }).orElse(false);
    }
}
