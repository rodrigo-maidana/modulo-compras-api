package modulocompras.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modulocompras.api.entities.Deposito;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Integer> {

    /**
     * Encuentra todos los depósitos que no están marcadas como eliminadas.
     *
     * @return una lista de depósitos que no están marcadas como eliminadas
     */
    List<Deposito> findByEliminadoFalse();

}