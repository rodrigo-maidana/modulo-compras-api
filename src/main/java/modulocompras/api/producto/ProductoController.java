package modulocompras.api.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import modulocompras.api.categoria.Categoria;
import modulocompras.api.marca.Marca;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos") // Endpoint para Productos
@Tag(name = "Productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository; // Inyección del repositorio de Producto

    // Obtener todos los productos
    @GetMapping
    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findByEliminadoFalse().stream()
                .map(producto -> new ProductoDTO(producto))
                .collect(Collectors.toList());
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Integer id) {
        Optional<Producto> producto = productoRepository.findByIdAndEliminadoFalse(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(new ProductoDTO(producto.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) {
        Producto newProducto = new Producto(productoDTO);
        Producto savedProducto = productoRepository.save(newProducto);
        return ResponseEntity.ok(new ProductoDTO(savedProducto));
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoDTO) {
        Optional<Producto> producto = productoRepository.findByIdAndEliminadoFalse(id);
        if (producto.isPresent()) {
            Producto existingProducto = producto.get();
            existingProducto.setDescripcion(productoDTO.getDescripcion());
            existingProducto.setCategoria(new Categoria(productoDTO.getCategoria()));
            existingProducto.setMarca(new Marca(productoDTO.getMarca()));
            Producto updatedProducto = productoRepository.save(existingProducto);
            return ResponseEntity.ok(new ProductoDTO(updatedProducto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProducto(@PathVariable Integer id) {
        return productoRepository.findByIdAndEliminadoFalse(id)
                .map(producto -> {
                    productoRepository.delete(producto);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
