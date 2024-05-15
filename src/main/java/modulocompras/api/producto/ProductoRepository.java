package modulocompras.api.producto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    /**
     * Obtiene una lista de productos que no han sido eliminados.
     *
     * @return la lista de productos no eliminados
     */
    public List<Producto> findByEliminadoFalse();

    /**
     * Obtiene un producto por su ID si no ha sido eliminado.
     *
     * @param id el ID del producto
     * @return el producto si existe y no ha sido eliminado, de lo contrario, un Optional vacío
     */
    Optional<Producto> findByIdAndEliminadoFalse(Integer id);
}