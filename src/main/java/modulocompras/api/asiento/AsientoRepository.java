package modulocompras.api.asiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Integer> {

    // Método para encontrar todos los asientos no eliminados
    List<Asiento> findByEliminadoFalse();

    // Método para encontrar un asiento por id si no está eliminado
    Optional<Asiento> findByIdAndEliminadoFalse(Integer id);
}
