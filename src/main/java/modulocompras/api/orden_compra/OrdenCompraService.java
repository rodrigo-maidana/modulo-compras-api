package modulocompras.api.orden_compra;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modulocompras.api.proveedor.Proveedor;

@Service
public class OrdenCompraService {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    // Crear una nueva orden de compra
    public OrdenCompra createOrdenCompra(OrdenCompraDTO ordenCompraDTO) {
        OrdenCompra newOrdenCompra = new OrdenCompra(ordenCompraDTO);
        newOrdenCompra.setFechaEmision(new Date());

        // Generar el número de orden de compra
        String nroOrdenCompra = generateNroOrdenCompra();
        newOrdenCompra.setNroOrdenCompra(nroOrdenCompra);
        newOrdenCompra.setEstado("Pendiente");
        newOrdenCompra.setProveedor(new Proveedor(ordenCompraDTO.getProveedor()));

        return ordenCompraRepository.save(newOrdenCompra);
    }

    // Generar el número de orden de compra
    private String generateNroOrdenCompra() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String currentDate = sdf.format(new Date());

        Date today = new Date(new java.util.Date().getTime());

        // Contar las órdenes de compra de hoy
        List<OrdenCompra> ordenesHoy = ordenCompraRepository.findByFechaEmision(today);

        int secuencia = ordenesHoy.size() + 1;

        // Formatear la secuencia a cuatro dígitos
        String secuenciaStr = String.format("%04d", secuencia);

        return "OC-" + currentDate + "-" + secuenciaStr;
    }

    // Obtener todas las órdenes de compra no eliminadas ordenadas por fecha de
    // emisión descendente
    public List<OrdenCompra> getAllOrdenesCompra() {
        return ordenCompraRepository.findByEliminadoFalseOrderByFechaEmisionDesc();
    }

    // Obtener una orden de compra por ID
    public Optional<OrdenCompra> getOrdenCompraById(Integer id) {
        return ordenCompraRepository.findByIdAndEliminadoFalse(id);
    }

    // Actualizar una orden de compra existente
    public Optional<OrdenCompra> updateOrdenCompra(Integer id, OrdenCompraDTO ordenCompraDetails) {
        return ordenCompraRepository.findByIdAndEliminadoFalse(id)
                .map(ordenCompra -> {
                    ordenCompra.setEstado(ordenCompraDetails.getEstado());
                    return ordenCompraRepository.save(ordenCompra);
                });
    }

    // Eliminar una orden de compra por ID
    public boolean deleteOrdenCompra(Integer id) {
        return ordenCompraRepository.findByIdAndEliminadoFalse(id)
                .map(ordenCompra -> {
                    ordenCompra.setEliminado(true);
                    ordenCompraRepository.save(ordenCompra);
                    return true;
                }).orElse(false);
    }

    // Preview de una orden de compra (sin guardar)
    public OrdenCompra previewOrdenCompra() {
        OrdenCompra ordenCompra = new OrdenCompra();
        ordenCompra.setFechaEmision(new Date());

        // Generar el numero de orden de compra
        String nroOrdenCompra = generateNroOrdenCompra();
        ordenCompra.setNroOrdenCompra(nroOrdenCompra);

        // Devolvemos la orden de compra sin guardar
        return ordenCompra;
    }

    public List<OrdenCompra> getOrdenesCompraPendientes() {
        return ordenCompraRepository.findByEstadoAndEliminadoFalse("Pendiente");
    }

}
