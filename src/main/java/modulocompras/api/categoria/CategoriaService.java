package modulocompras.api.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findByEliminadoFalse().stream()
                .map(categoria -> new CategoriaDTO(categoria))
                .collect(Collectors.toList());
    }

    public Optional<CategoriaDTO> getCategoriaById(Integer id) {
        return categoriaRepository.findByIdAndEliminadoFalse(id)
                .map(categoria -> new CategoriaDTO(categoria));
    }

    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) {
        Categoria newCategoria = new Categoria(categoriaDTO);
        Categoria savedCategoria = categoriaRepository.save(newCategoria);
        return new CategoriaDTO(savedCategoria);
    }

    public Optional<CategoriaDTO> updateCategoria(Integer id, CategoriaDTO categoriaDTO) {
        return categoriaRepository.findByIdAndEliminadoFalse(id)
                .map(existingCategoria -> {
                    existingCategoria.setNombre(categoriaDTO.getNombre());
                    Categoria updatedCategoria = categoriaRepository.save(existingCategoria);
                    return new CategoriaDTO(updatedCategoria);
                });
    }

    public boolean deleteCategoria(Integer id) {
        return categoriaRepository.findByIdAndEliminadoFalse(id)
                .map(categoria -> {
                    categoria.setEliminado(true);
                    categoriaRepository.save(categoria);
                    return true;
                }).orElse(false);
    }
}
