package modulocompras.api.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos") // Endpoint para Productos
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository; // Inyección del repositorio de Producto

    // Obtener todos los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo producto
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Integer id, @RequestBody Producto productoDetails) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setMarca(productoDetails.getMarca());
                    producto.setCategoria(productoDetails.getCategoria());
                    producto.setDescripcion(productoDetails.getDescripcion());
                    Producto updatedProducto = productoRepository.save(producto);
                    return ResponseEntity.ok(updatedProducto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        return productoRepository.findById(id)
                .map(producto -> {
                    productoRepository.delete(producto);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
