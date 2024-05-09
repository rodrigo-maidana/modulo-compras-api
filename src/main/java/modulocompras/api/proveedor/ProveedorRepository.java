package modulocompras.api.proveedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    // Aquí puedes añadir métodos personalizados de consulta si es necesario
}