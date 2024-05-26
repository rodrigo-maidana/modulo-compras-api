package modulocompras.api.proveedor_categoria;

import modulocompras.api.proveedor.Proveedor;
import modulocompras.api.proveedor.ProveedorDTO;
import modulocompras.api.proveedor.ProveedorService;
import modulocompras.api.categoria.Categoria;
import modulocompras.api.categoria.CategoriaDTO;
import modulocompras.api.categoria.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorCategoriaService {

    @Autowired
    private ProveedorCategoriaRepository proveedorCategoriaRepository;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private CategoriaService categoriaService;

    // Agregar una categoría a un proveedor
    public ProveedorCategoriaDTO addCategoriaToProveedor(ProveedorCategoriaDTO proveedorCategoriaDTO) {
        Optional<ProveedorDTO> optionalProveedor = proveedorService
                .getProveedorById(proveedorCategoriaDTO.getProveedorId());
        if (!optionalProveedor.isPresent()) {
            return null;
        }
        Optional<CategoriaDTO> optionalCategoria = categoriaService
                .getCategoriaById(proveedorCategoriaDTO.getCategoriaId());
        if (!optionalCategoria.isPresent()) {
            return null;
        }

        Optional<ProveedorCategoria> optionalProveedorCategoria = proveedorCategoriaRepository
                .findByProveedorAndCategoria(new Proveedor(optionalProveedor.get()),
                        new Categoria(optionalCategoria.get()));
        if (optionalProveedorCategoria.isPresent()) {
            return new ProveedorCategoriaDTO(optionalProveedorCategoria.get());
        }

        Proveedor proveedor = new Proveedor(optionalProveedor.get());
        Categoria categoria = new Categoria(optionalCategoria.get());

        ProveedorCategoria savedProveedorCategoria = proveedorCategoriaRepository
                .save(new ProveedorCategoria(proveedor, categoria));
        return new ProveedorCategoriaDTO(savedProveedorCategoria);
    }

    // Listar todas las categorías de un proveedor
    public List<CategoriaDTO> getCategorias(Integer idProveedor) {
        Optional<ProveedorDTO> optionalProveedor = proveedorService.getProveedorById(idProveedor);
        if (!optionalProveedor.isPresent()) {
            return Collections.emptyList();
        }

        Proveedor proveedor = new Proveedor(optionalProveedor.get());
        List<CategoriaDTO> categorias = proveedorCategoriaRepository.findByProveedor(proveedor).stream()
                .map(proveedorCategoria -> new CategoriaDTO(proveedorCategoria.getCategoria()))
                .collect(Collectors.toList());

        return categorias;
    }

    // Listar todos los proveedores de una categoría
    public List<ProveedorDTO> getProveedores(Integer idCategoria) {
        Optional<CategoriaDTO> optionalCategoria = categoriaService.getCategoriaById(idCategoria);
        if (!optionalCategoria.isPresent()) {
            return Collections.emptyList();
        }

        Categoria categoria = new Categoria(optionalCategoria.get());
        List<ProveedorDTO> proveedores = proveedorCategoriaRepository.findByCategoria(categoria).stream()
                .map(proveedorCategoria -> new ProveedorDTO(proveedorCategoria.getProveedor()))
                .collect(Collectors.toList());

        return proveedores;
    }

}
