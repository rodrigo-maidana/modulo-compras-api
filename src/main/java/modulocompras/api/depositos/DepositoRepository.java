package modulocompras.api.depositos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Integer> {

    /**
     * Encuentra todos los depósitos que no están marcadas como eliminadas.
     *
     * @return una lista de depósitos que no están marcadas como eliminadas
     */
    List<Deposito> findByEliminadoFalse();

    /**
     * Encuentra un depósito por su ID que no está marcado como eliminado.
     *
     * @param id el ID del depósito
     * @return un depósito que no está marcado como eliminado
     */
    Optional<Deposito> findByIdAndEliminadoFalse(Integer id);

}