package modulocompras.api.factura.detalle;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaDetalleRepository extends JpaRepository<FacturaDetalle, Integer> {

    /**
     * Obtiene una lista de todos los detalles de factura no eliminados.
     *
     * @return Lista de detalles de factura no eliminados.
     */
    List<FacturaDetalle> findByEliminadoFalse();

    /**
     * Obtiene el detalle de factura con el ID especificado que no ha sido
     * eliminado.
     *
     * @param id El ID del detalle de factura.
     * @return El detalle de factura con el ID especificado, si existe y no ha sido
     *         eliminado.
     */
    Optional<FacturaDetalle> findByIdAndEliminadoFalse(Integer id);

    /**
     * Obtiene una lista de todos los detalles de factura asociados a una factura
     * específica.
     *
     * @param idFactura El ID de la factura.
     * @return Lista de detalles de factura asociados a la factura especificada.
     */
    List<FacturaDetalle> findByFacturaId(Integer idFactura);

    /**
     * Obtiene una lista de todos los detalles de factura asociados a una factura
     * específica que no han sido eliminados.
     *
     * @param idFactura El ID de la factura.
     * @return Lista de detalles de factura asociados a la factura especificada y no
     *         eliminados.
     */
    List<FacturaDetalle> findByFacturaIdAndEliminadoFalse(Integer idFactura);

}
