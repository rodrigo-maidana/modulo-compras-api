package modulocompras.api.asiento.detalle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsientoDetalleRepository extends JpaRepository<AsientoDetalle, Integer> {

    // Método para encontrar todos los detalles de asientos no eliminados
    List<AsientoDetalle> findByEliminadoFalse();

    // Método para encontrar un detalle de asiento por id si no está eliminado
    Optional<AsientoDetalle> findByIdAndEliminadoFalse(Integer id);
}
