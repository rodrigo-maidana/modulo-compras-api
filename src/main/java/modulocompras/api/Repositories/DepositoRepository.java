package modulocompras.api.Repositories;

import modulocompras.api.Entities.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Integer> {
    // Aquí puedes añadir métodos personalizados de consulta si es necesario
    
}