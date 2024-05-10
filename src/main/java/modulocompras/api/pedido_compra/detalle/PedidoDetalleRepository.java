package modulocompras.api.pedido_compra.detalle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Integer> {

    /**
     * Busca y devuelve una lista de los detalles de pedido que no han sido
     * eliminados.
     *
     * @return una lista de los detalles de pedido que no han sido eliminados.
     */
    List<PedidoDetalle> findByEliminadoFalse();

    /**
     * Encuentra todos los detalles de un pedido de compra basados en el ID del
     * pedido.
     *
     * @param idPedidoCompra el ID del pedido de compra.
     * @return una lista de detalles de pedido, o una lista vacía si no se
     *         encuentran detalles.
     */
    List<PedidoDetalle> findByPedidoCompraId(Integer idPedidoCompra);

    /**
     * Busca los detalles de un pedido de compra por su ID de pedido de compra,
     * excluyendo los eliminados.
     *
     * @param idPedidoCompra El ID del pedido de compra.
     * @return Una lista de los detalles del pedido de compra encontrados.
     */
    List<PedidoDetalle> findByPedidoCompraIdAndEliminadoFalse(Integer idPedidoCompra);

}