package modulocompras.api.marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<MarcaDTO> getAllMarcas() {
        return marcaRepository.findByEliminadoFalse().stream()
                .map(marca -> new MarcaDTO(marca))
                .collect(Collectors.toList());
    }

    public Optional<MarcaDTO> getMarcaById(Integer id) {
        return marcaRepository.findByIdAndEliminadoFalse(id)
                .map(marca -> new MarcaDTO(marca));
    }

    public MarcaDTO createMarca(MarcaDTO marcaDTO) {
        Marca newMarca = new Marca(marcaDTO);
        Marca savedMarca = marcaRepository.save(newMarca);
        return new MarcaDTO(savedMarca);
    }

    public Optional<MarcaDTO> updateMarca(Integer id, MarcaDTO marcaDTO) {
        return marcaRepository.findByIdAndEliminadoFalse(id)
                .map(existingMarca -> {
                    existingMarca.setNombre(marcaDTO.getNombre());
                    Marca updatedMarca = marcaRepository.save(existingMarca);
                    return new MarcaDTO(updatedMarca);
                });
    }

    public boolean deleteMarca(Integer id) {
        return marcaRepository.findByIdAndEliminadoFalse(id)
                .map(marca -> {
                    marca.setEliminado(true);
                    marcaRepository.save(marca);
                    return true;
                }).orElse(false);
    }
}
