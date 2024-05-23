package modulocompras.api.producto;

import modulocompras.api.categoria.Categoria;
import modulocompras.api.categoria.CategoriaDTO;
import modulocompras.api.categoria.CategoriaService;
import modulocompras.api.marca.Marca;
import modulocompras.api.marca.MarcaDTO;
import modulocompras.api.marca.MarcaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private CategoriaService categoriaService;

    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findByEliminadoFalse().stream()
                .map(ProductoDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<ProductoDTO> getProductoById(Integer id) {
        return productoRepository.findByIdAndEliminadoFalse(id)
                .map(ProductoDTO::new);
    }

    public Optional<ProductoDTO> createProducto(ProductoDTO productoDTO) {
        Optional<MarcaDTO> optionalMarca = marcaService.getMarcaById(productoDTO.getMarca().getId());
        Optional<CategoriaDTO> optionalCategoria = categoriaService
                .getCategoriaById(productoDTO.getCategoria().getId());

        if (!optionalMarca.isPresent() || !optionalCategoria.isPresent()) {
            return Optional.empty();
        }

        Producto newProducto = new Producto(productoDTO);
        Categoria categoria = new Categoria(optionalCategoria.get());
        Marca marca = new Marca(optionalMarca.get());

        newProducto.setCategoria(categoria);
        newProducto.setMarca(marca);

        Producto savedProducto = productoRepository.save(newProducto);
        return Optional.of(new ProductoDTO(savedProducto));
    }

    public Optional<ProductoDTO> updateProducto(Integer id, ProductoDTO productoDTO) {
        Optional<Producto> optionalProducto = productoRepository.findByIdAndEliminadoFalse(id);
        Optional<MarcaDTO> optionalMarca = marcaService.getMarcaById(productoDTO.getMarca().getId());
        Optional<CategoriaDTO> optionalCategoria = categoriaService
                .getCategoriaById(productoDTO.getCategoria().getId());

        if (!optionalProducto.isPresent() || !optionalMarca.isPresent() || !optionalCategoria.isPresent()) {
            return Optional.empty();
        }

        Producto producto = optionalProducto.get();
        Categoria categoria = new Categoria(optionalCategoria.get());
        Marca marca = new Marca(optionalMarca.get());

        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setCategoria(categoria);
        producto.setMarca(marca);

        Producto updatedProducto = productoRepository.save(producto);

        return Optional.of(new ProductoDTO(updatedProducto));
    }

    public boolean deleteProducto(Integer id) {
        return productoRepository.findByIdAndEliminadoFalse(id)
                .map(producto -> {
                    productoRepository.delete(producto);
                    return true;
                })
                .orElse(false);
    }
}
