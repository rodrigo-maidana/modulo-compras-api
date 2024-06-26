package modulocompras.api.pedido_compra;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
     * Busca y devuelve un pedido de compra con el ID especificado que no ha sido
     * eliminado.
     *
     * @param id El ID del pedido de compra a buscar.
     * @return Un Optional que contiene el pedido de compra con el ID especificado,
     *         si existe y no ha sido eliminado.
     */
    public Optional<PedidoCompra> findByIdAndEliminadoFalse(Integer id);

    /**
     * Busca y devuelve una lista de pedidos de compra que no han sido eliminados,
     * ordenados de más nuevo a más viejo por fecha de emisión.
     *
     * @return Una lista de pedidos de compra no eliminados, ordenados por fecha de
     *         emisión.
     */
    public List<PedidoCompra> findByEliminadoFalseOrderByFechaEmisionDesc();

    /**
     * Obtiene una lista de pedidos de compra por su fecha de emisión.
     *
     * @param fecha la fecha de emisión
     * @return la lista de pedidos de compra por su fecha de emisión
     */
    @Query("SELECT p FROM PedidoCompra p WHERE DATE(p.fechaEmision) = DATE(:fecha)")
    List<PedidoCompra> findByFechaEmision(@Param("fecha") Date fecha);

    /**
     * Obtiene una lista de pedidos de compra por su estado.
     *
     * @param estado el estado del pedido de compra
     * @return la lista de pedidos de compra por su estado
     */
    public List<PedidoCompra> findByEstadoAndEliminadoFalse(String string);

}