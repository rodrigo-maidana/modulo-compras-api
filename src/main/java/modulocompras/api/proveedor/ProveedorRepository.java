package modulocompras.api.proveedor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    List<Proveedor> findByEliminadoFalse();

    Optional<Proveedor> findByIdAndEliminadoFalse(Integer id);
}