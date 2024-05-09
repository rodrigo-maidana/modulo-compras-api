package modulocompras.api.pedido_compra.detalle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Integer> {

    /**
     * Encuentra todos los detalles de un pedido de compra basados en el ID del
     * pedido.
     *
     * @param idPedidoCompra el ID del pedido de compra.
     * @return una lista de detalles de pedido, o una lista vacía si no se
     *         encuentran detalles.
     */
    List<PedidoDetalle> findByPedidoCompraId(Integer idPedidoCompra);

}