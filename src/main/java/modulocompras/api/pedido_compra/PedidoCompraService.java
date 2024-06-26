package modulocompras.api.pedido_compra;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoCompraService {

    @Autowired
    private PedidoCompraRepository pedidoCompraRepository;

    // Crear un nuevo pedido de compra
    public PedidoCompraDTO createPedidoCompra(PedidoCompraDTO pedidoCompraDTO) {
        PedidoCompra newPedidoCompra = new PedidoCompra(pedidoCompraDTO);
        newPedidoCompra.setFechaEmision(new Date());

        // Generar el número de pedido
        String nroPedido = generateNroPedido();
        newPedidoCompra.setNroPedido(nroPedido);
        newPedidoCompra.setEstado("Pendiente");

        PedidoCompra savedPedidoCompra = pedidoCompraRepository.save(newPedidoCompra);
        return new PedidoCompraDTO(savedPedidoCompra);
    }

    // Generar el número de pedido
    private String generateNroPedido() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String currentDate = sdf.format(new Date());

        Date today = new Date(new java.util.Date().getTime());

        // Contar los pedidos de hoy
        List<PedidoCompra> pedidosHoy = pedidoCompraRepository.findByFechaEmision(today);

        int secuencia = pedidosHoy.size() + 1;

        // Formatear la secuencia a cuatro dígitos
        String secuenciaStr = String.format("%04d", secuencia);

        return "PC-" + currentDate + "-" + secuenciaStr;
    }

    // Obtener todos los pedidos de compra no eliminados ordenados por fecha de
    // emisión descendente
    public List<PedidoCompraDTO> getAllPedidosCompra() {
        return pedidoCompraRepository.findByEliminadoFalseOrderByFechaEmisionDesc().stream()
                .map(pedidoCompra -> new PedidoCompraDTO(pedidoCompra))
                .collect(Collectors.toList());
    }

    // Obtener un pedido de compra por ID
    public Optional<PedidoCompra> getPedidoCompraById(Integer id) {
        return pedidoCompraRepository.findByIdAndEliminadoFalse(id);
    }

    // Actualizar un pedido de compra por ID
    public Optional<PedidoCompraDTO> updatePedidoCompra(Integer id, PedidoCompraDTO pedidoCompraDetails) {
        return pedidoCompraRepository.findByIdAndEliminadoFalse(id)
                .map(existingPedidoCompra -> {
                    existingPedidoCompra.setEstado(pedidoCompraDetails.getEstado());
                    PedidoCompra updatedPedidoCompra = pedidoCompraRepository.save(existingPedidoCompra);
                    return new PedidoCompraDTO(updatedPedidoCompra);
                });
    }

    // Eliminar un pedido de compra por ID
    public boolean deletePedidoCompra(Integer id) {
        return pedidoCompraRepository.findByIdAndEliminadoFalse(id)
                .map(pedidoCompra -> {
                    pedidoCompra.setEliminado(true);
                    pedidoCompraRepository.save(pedidoCompra);
                    return true;
                }).orElse(false);
    }

    // Preview de un pedido de compra (sin guardar)
    public PedidoCompraDTO previewPedidoCompra() {
        PedidoCompra newPedidoCompra = new PedidoCompra();
        newPedidoCompra.setFechaEmision(new Date());

        // Generar el número de pedido
        String nroPedido = generateNroPedido();
        newPedidoCompra.setNroPedido(nroPedido);

        // Devolver un DTO sin guardar
        return new PedidoCompraDTO(newPedidoCompra);
    }

    // Obtener Pedidos De Compra con estado pendiente
    public List<PedidoCompra> getPedidosCompraPendientes() {
        return pedidoCompraRepository.findByEstadoAndEliminadoFalse("Pendiente");
    }

}
