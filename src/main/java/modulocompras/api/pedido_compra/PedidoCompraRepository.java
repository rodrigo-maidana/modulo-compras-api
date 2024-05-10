package modulocompras.api.pedido_compra;

import java.util.List;

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

}