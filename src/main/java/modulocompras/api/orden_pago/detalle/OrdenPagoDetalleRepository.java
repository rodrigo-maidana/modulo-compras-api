package modulocompras.api.orden_pago.detalle;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenPagoDetalleRepository extends JpaRepository<OrdenPagoDetalle, Integer> {

    /**
     * Retrieves a list of OrdenPagoDetalle objects that are not marked as deleted
     * for a given ordenPagoId.
     *
     * @param ordenPagoId the ID of the OrdenPago
     * @return a list of OrdenPagoDetalle objects
     */
    List<OrdenPagoDetalle> findByEliminadoFalse();

    /**
     * Retrieves an Optional containing the OrdenPagoDetalle object with the
     * specified ID that is not marked as deleted.
     *
     * @param id the ID of the OrdenPagoDetalle
     * @return an Optional containing the OrdenPagoDetalle object, or an empty
     *         Optional if not found
     */
    Optional<OrdenPagoDetalle> findByIdAndEliminadoFalse(Integer id);

    /**
     * Retrieves a list of OrdenPagoDetalle objects for a given ordenPagoId that are
     * not marked as deleted.
     *
     * @param ordenPagoId the ID of the OrdenPago
     * @return a list of OrdenPagoDetalle objects
     */
    List<OrdenPagoDetalle> findByOrdenPagoIdAndEliminadoFalse(Integer ordenPagoId);

}
