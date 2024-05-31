package modulocompras.api.cotizacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {

    /**
     * Obtiene una lista de pedidos de cotización que no han sido eliminados.
     *
     * @return la lista de pedidos de cotización no eliminados
     */
    public List<Cotizacion> findByEliminadoFalse();

    /**
     * Obtiene un pedido de cotización por su ID si no ha sido eliminado.
     *
     * @param id el ID del pedido de cotización
     * @return el pedido de cotización si existe y no ha sido eliminado, de lo
     *         contrario, un Optional vacío
     */
    Optional<Cotizacion> findByIdAndEliminadoFalse(Integer id);

    /**
     * Busca y devuelve una lista de pedidos de compra que no han sido eliminados,
     * ordenados de más nuevo a más viejo por fecha de emisión.
     *
     * @return Una lista de pedidos de compra no eliminados, ordenados por fecha de
     *         emisión.
     */
    public List<Cotizacion> findByEliminadoFalseOrderByFechaEmisionDesc();

    /**
     * Obtiene una lista de pedidos de cotización por su fecha de emisión.
     *
     * @param fecha la fecha de emisión
     * @return la lista de pedidos de cotización por su fecha de emisión
     */
    @Query("SELECT p FROM Cotizacion p WHERE DATE(p.fechaEmision) = DATE(:fecha)")
    public List<Cotizacion> findByFechaEmision(@Param("fecha") Date fecha);

    /**
     * Verifica si existe un pedido de cotización por su ID y si no ha sido
     * eliminado.
     *
     * @param id el ID del pedido de cotización
     * @return true si existe y no ha sido eliminado, de lo contrario, false
     */
    public boolean existsByPedidoCompraIdAndProveedorIdAndEliminadoFalse(Integer pedidoCompraId, Integer proveedorId);

    /**
     * Obtiene una lista de pedidos de cotización por su ID de pedido de compra.
     *
     * @param id el ID del pedido de compra
     * @return la lista de pedidos de cotización por su ID de pedido de compra
     */
    public List<Cotizacion> findByPedidoCompraIdAndEliminadoFalse(Integer id);

}
