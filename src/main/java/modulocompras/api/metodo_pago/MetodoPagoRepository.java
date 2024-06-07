package modulocompras.api.metodo_pago;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {

    /**
     * Obtiene una lista de todos los métodos de pago que no han sido eliminados.
     *
     * @return Lista de métodos de pago no eliminados.
     */
    List<MetodoPago> findByEliminadoFalse();

    /**
     * Obtiene un método de pago por su ID si no ha sido eliminado.
     *
     * @param id El ID del método de pago.
     * @return Método de pago correspondiente al ID especificado, si existe y no ha
     *         sido eliminado.
     */
    Optional<MetodoPago> findByIdAndEliminadoFalse(Integer id);
}
