package modulocompras.api.pedido_compra;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoCompraRepository extends JpaRepository<PedidoCompra, Integer> {

    /**
     * Busca y devuelve una lista de pedidos de compra que no han sido eliminados.
     *
     * @return Una lista de pedidos de compra no eliminados.
     */
    public List<PedidoCompra> findByEliminadoFalse();

    /**
     * Busca y devuelve una lista de pedidos de compra que no han sido eliminados,
     * ordenados de más nuevo a más viejo por fecha de emisión.
     *
     * @return Una lista de pedidos de compra no eliminados, ordenados por fecha de
     *         emisión.
     */
    public List<PedidoCompra> findByEliminadoFalseOrderByFechaEmisionDesc();

    Optional<PedidoCompra> findByIdAndEliminadoFalse(Integer id);

}