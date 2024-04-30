package modulocompras.api.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Aquí puedes añadir métodos personalizados de consulta si es necesario
    
}