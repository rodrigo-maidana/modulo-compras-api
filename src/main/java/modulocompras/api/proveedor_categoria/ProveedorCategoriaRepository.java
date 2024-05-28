package modulocompras.api.proveedor_categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modulocompras.api.categoria.Categoria;
import modulocompras.api.proveedor.Proveedor;

@Repository
public interface ProveedorCategoriaRepository extends JpaRepository<ProveedorCategoria, Integer> {

    // Listar todas las categorías de un proveedor
    List<ProveedorCategoria> findByProveedor(Proveedor proveedor);

    // Listar todas los proveedores de una categoría
    List<ProveedorCategoria> findByCategoria(Categoria categoria);

    // Buscar una relación proveedor-categoría
    Optional<ProveedorCategoria> findByProveedorAndCategoria(Proveedor proveedor, Categoria categoria);

    // Verificar si existe una relación proveedor-categoría
    boolean existsByProveedorIdAndCategoriaId(Integer proveedorId, Integer categoriaId);
}