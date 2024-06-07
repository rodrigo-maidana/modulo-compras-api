package modulocompras.api.orden_pago;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import modulocompras.api.pedido_compra.PedidoCompra;

@Repository
public interface OrdenPagoRepository extends JpaRepository<OrdenPago, Integer> {

    /**
     * Obtiene una lista de todas las órdenes de pago no eliminadas.
     *
     * @return Lista de órdenes de pago no eliminadas.
     */
    public List<OrdenPago> findByEliminadoFalse();

    /**
     * Obtiene una orden de pago por su ID si no ha sido eliminada.
     *
     * @param idOrdenPago El ID de la orden de pago.
     * @return La orden de pago correspondiente al ID especificado, si existe y no
     *         ha sido eliminada.
     */
    public Optional<OrdenPago> findByIdAndEliminadoFalse(Integer idOrdenPago);

    /**
     * Obtiene una lista de todas las órdenes de pago no eliminadas, ordenadas por
     * fecha de pago en orden descendente.
     *
     * @return Lista de órdenes de pago no eliminadas ordenadas por fecha de pago
     *         descendente.
     */
    public List<OrdenPago> findByEliminadoFalseOrderByFechaPagoDesc();

    /**
     * Obtiene una lista de órdenes de pago cuya fecha de pago coincide con la fecha
     * especificada.
     *
     * @param fecha La fecha de pago a buscar.
     * @return Lista de órdenes de pago cuya fecha de pago coincide con la fecha
     *         especificada.
     */
    @Query("SELECT o FROM OrdenPago o WHERE DATE(o.fechaPago) = DATE(:fecha)")
    List<OrdenPago> findByfechaPago(@Param("fecha") Date fecha);
}
