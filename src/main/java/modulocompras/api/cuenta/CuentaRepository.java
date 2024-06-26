package modulocompras.api.cuenta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    // Aquí puedes añadir métodos de consulta personalizados si los necesitas
}
