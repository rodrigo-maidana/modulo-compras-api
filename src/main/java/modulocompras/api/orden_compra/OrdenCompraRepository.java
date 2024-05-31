package modulocompras.api.orden_compra;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Integer> {

    /**
     * Obtiene una lista de todas las órdenes de compra que no han sido eliminadas.
     * 
     * @return una lista de órdenes de compra no eliminadas
     */
    public List<OrdenCompra> findByEliminadoFalse();

    /**
     * Obtiene una orden de compra por su ID si no ha sido eliminada.
     * 
     * @param id el ID de la orden de compra
     * @return una orden de compra opcional si existe y no ha sido eliminada, de lo
     *         contrario, vacío
     */
    public Optional<OrdenCompra> findByIdAndEliminadoFalse(Integer id);

    /**
     * Obtiene una lista de todas las órdenes de compra que no han sido eliminadas,
     * ordenadas por fecha de emisión en orden descendente.
     * 
     * @return una lista de órdenes de compra no eliminadas ordenadas por fecha de
     *         emisión descendente
     */
    public List<OrdenCompra> findByEliminadoFalseOrderByFechaEmisionDesc();

    /**
     * Obtiene una lista de todas las órdenes de compra cuya fecha de emisión
     * coincide con la fecha especificada.
     * 
     * @param fecha la fecha de emisión para buscar órdenes de compra
     * @return una lista de órdenes de compra cuya fecha de emisión coincide con la
     *         fecha especificada
     */
    @Query("SELECT o FROM OrdenCompra o WHERE DATE(o.fechaEmision) = DATE(:fecha)")
    List<OrdenCompra> findByFechaEmision(@Param("fecha") Date fecha);
}
