package modulocompras.api.cotizacion.detalle;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotizacionDetalleRepository extends JpaRepository<CotizacionDetalle, Integer> {

    /**
     * Busca y devuelve una lista de los detalles de pedido que no han sido
     * eliminados.
     *
     * @return una lista de los detalles de pedido que no han sido eliminados.
     */
    List<CotizacionDetalle> findByEliminadoFalse();

    /**
     * Busca un PedidoCompra por su ID y verifica que no haya sido eliminado.
     *
     * @param id El ID del PedidoCompra a buscar.
     * @return Un Optional que contiene el PedidoCompra si se encuentra y no ha sido
     *         eliminado, o vacío si no se encuentra o ha sido eliminado.
     */
    Optional<CotizacionDetalle> findByIdAndEliminadoFalse(Integer id);

    /**
     * Encuentra todos los detalles de un pedido de compra basados en el ID del
     * pedido.
     *
     * @param idPedidoCompra el ID del pedido de compra.
     * @return una lista de detalles de pedido, o una lista vacía si no se
     *         encuentran detalles.
     */
    List<CotizacionDetalle> findByCotizacionId(Integer idPedidoCompra);

    /**
     * Busca los detalles de un pedido de compra por su ID de pedido de compra,
     * excluyendo los eliminados.
     *
     * @param idPedidoCompra El ID del pedido de compra.
     * @return Una lista de los detalles del pedido de compra encontrados.
     */
    List<CotizacionDetalle> findByCotizacionIdAndEliminadoFalse(Integer id);

}