package modulocompras.api.orden_compra.detalles;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalle, Integer> {

    /**
     * Obtiene una lista de todos los detalles de órdenes de compra que no han sido
     * eliminados.
     * 
     * @return Una lista de detalles de órdenes de compra no eliminados.
     */
    List<OrdenDetalle> findByEliminadoFalse();

    /**
     * Obtiene el detalle de orden de compra con el ID especificado que no ha sido
     * eliminado.
     * 
     * @param id El ID del detalle de orden de compra.
     * @return Un Optional que contiene el detalle de orden de compra si existe, o
     *         vacío si no existe.
     */
    Optional<OrdenDetalle> findByIdAndEliminadoFalse(Integer id);

    /**
     * Obtiene una lista de todos los detalles de órdenes de compra asociados a una
     * orden de compra específica.
     * 
     * @param idOrdenCompra El ID de la orden de compra.
     * @return Una lista de detalles de órdenes de compra asociados a la orden de
     *         compra especificada.
     */
    List<OrdenDetalle> findByOrdenCompraId(Integer idOrdenCompra);

    /**
     * Obtiene una lista de todos los detalles de órdenes de compra asociados a una
     * orden de compra específica que no han sido eliminados.
     * 
     * @param idOrdenCompra El ID de la orden de compra.
     * @return Una lista de detalles de órdenes de compra asociados a la orden de
     *         compra especificada y no eliminados.
     */
    List<OrdenDetalle> findByOrdenCompraIdAndEliminadoFalse(Integer idOrdenCompra);

}
