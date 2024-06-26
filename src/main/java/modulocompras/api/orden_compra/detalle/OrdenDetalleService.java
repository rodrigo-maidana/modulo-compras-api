package modulocompras.api.orden_compra.detalle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import modulocompras.api.orden_compra.OrdenCompra;
import modulocompras.api.orden_compra.OrdenCompraService;

@Service
public class OrdenDetalleService {

    @Autowired
    private OrdenDetalleRepository ordenDetalleRepository;

    @Autowired
    @Lazy
    private OrdenCompraService ordenCompraService;

    // Método para obtener todos los detalles de órdenes de compra
    public List<OrdenDetalle> getAllOrdenesDetalles() {
        return ordenDetalleRepository.findByEliminadoFalse();
    }

    // Método para obtener un detalle de orden de compra por su ID
    public Optional<OrdenDetalle> getOrdenDetalleById(Integer id) {
        return ordenDetalleRepository.findByIdAndEliminadoFalse(id);
    }

    // Método para crear un detalle de orden de compra
    public Optional<OrdenDetalle> createOrdenDetalle(Integer id, OrdenDetalleDTO ordenDetalleDTO) {
        OrdenCompra ordenCompra = ordenCompraService.getOrdenCompraById(id).orElse(null);
        if (ordenCompra == null) {
            return Optional.empty();
        }
        OrdenDetalle newOrdenDetalle = new OrdenDetalle(ordenDetalleDTO);
        newOrdenDetalle.setOrdenCompra(ordenCompra);
        return Optional.of(ordenDetalleRepository.save(newOrdenDetalle));
    }

    // Método para actualizar un detalle de orden de compra
    public Optional<OrdenDetalle> updateOrdenDetalle(Integer id, OrdenDetalleDTO ordenDetalle) {
        return getOrdenDetalleById(id).map(ordenDetalleObj -> {
            ordenDetalleObj.setCantidad(ordenDetalle.getCantidad());
            return Optional.of(ordenDetalleRepository.save(ordenDetalleObj));
        }).orElse(Optional.empty());
    }

    // Método para eliminar un detalle de orden de compra
    public boolean deleteOrdenDetalle(Integer id) {
        return getOrdenDetalleById(id)
                .map(ordenDetalle -> {
                    ordenDetalle.setEliminado(true);
                    ordenDetalleRepository.save(ordenDetalle);
                    return true;
                }).orElse(false);
    }

    // Método para obtener todos los detalles de una orden de compra
    public List<OrdenDetalle> getDetallesByOrdenCompraId(Integer idOrdenCompra) {
        return ordenDetalleRepository.findByOrdenCompraIdAndEliminadoFalse(idOrdenCompra);
    }

}
