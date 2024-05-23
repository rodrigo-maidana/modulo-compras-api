package modulocompras.api.producto;

import modulocompras.api.categoria.Categoria;
import modulocompras.api.marca.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findByEliminadoFalse().stream()
                .map(ProductoDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<ProductoDTO> getProductoById(Integer id) {
        return productoRepository.findByIdAndEliminadoFalse(id)
                .map(ProductoDTO::new);
    }

    public ProductoDTO createProducto(ProductoDTO productoDTO) {
        Producto newProducto = new Producto(productoDTO);
        Producto savedProducto = productoRepository.save(newProducto);
        return new ProductoDTO(savedProducto);
    }

    public Optional<ProductoDTO> updateProducto(Integer id, ProductoDTO productoDTO) {
        return productoRepository.findByIdAndEliminadoFalse(id)
                .map(producto -> {
                    producto.setDescripcion(productoDTO.getDescripcion());
                    producto.setCategoria(new Categoria(productoDTO.getCategoria()));
                    producto.setMarca(new Marca(productoDTO.getMarca()));
                    Producto updatedProducto = productoRepository.save(producto);
                    return new ProductoDTO(updatedProducto);
                });
    }

    public boolean deleteProducto(Integer id) {
        return productoRepository.findByIdAndEliminadoFalse(id)
                .map(producto -> {
                    productoRepository.delete(producto);
                    return true;
                })
                .orElse(false);
    }

    public Optional<Producto> getProductoByDTO(ProductoDTO productoDTO) {
        Integer id = productoDTO.getId();
        return productoRepository.findByIdAndEliminadoFalse(id);
    }
}
