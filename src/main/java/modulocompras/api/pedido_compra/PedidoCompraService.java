package modulocompras.api.pedido_compra;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PedidoCompraService {

    private PedidoCompraRepository pedidoCompraRepository;

    public PedidoCompraService(PedidoCompraRepository pedidoCompraRepository) {
        this.pedidoCompraRepository = pedidoCompraRepository;
    }

    // Crear un nuevo pedido de compra
    public PedidoCompra createPedidoCompra(PedidoCompraDTO pedidoCompraDTO) {
        PedidoCompra newPedidoCompra = new PedidoCompra(pedidoCompraDTO);
        newPedidoCompra.setFechaEmision(new Date());

        // Generar el número de pedido
        String nroPedido = generateNroPedido();
        newPedidoCompra.setNroPedido(nroPedido);

        return pedidoCompraRepository.save(newPedidoCompra);
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
    public ResponseEntity<PedidoCompraDTO> getPedidoCompraById(Integer id) {
        Optional<PedidoCompra> pedidoCompra = pedidoCompraRepository.findByIdAndEliminadoFalse(id);
        if (pedidoCompra.isPresent()) {
            return ResponseEntity.ok(new PedidoCompraDTO(pedidoCompra.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar un pedido de compra por ID
    public ResponseEntity<PedidoCompraDTO> updatePedidoCompra(Integer id, PedidoCompraDTO pedidoCompraDetails) {
        Optional<PedidoCompra> pedidoCompra = pedidoCompraRepository.findByIdAndEliminadoFalse(id);
        if (pedidoCompra.isPresent()) {
            PedidoCompra existingPedidoCompra = pedidoCompra.get();
            existingPedidoCompra.setEstado(pedidoCompraDetails.getEstado());
            PedidoCompra updatedPedidoCompra = pedidoCompraRepository.save(existingPedidoCompra);
            return ResponseEntity.ok(new PedidoCompraDTO(updatedPedidoCompra));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un pedido de compra por ID
    public ResponseEntity<Object> softDeletePedidoCompra(Integer id) {
        return pedidoCompraRepository.findByIdAndEliminadoFalse(id).map(pedidoCompra -> {
            pedidoCompra.setEliminado(true);
            pedidoCompraRepository.save(pedidoCompra);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
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

    public Optional<PedidoCompra> findByIdAndEliminadoFalse(Integer id) {
        return pedidoCompraRepository.findByIdAndEliminadoFalse(id);
    }
}
