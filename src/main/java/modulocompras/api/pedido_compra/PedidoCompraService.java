package modulocompras.api.pedido_compra;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

        // Contar los pedidos de hoy
        List<PedidoCompra> pedidosHoy = pedidoCompraRepository.findByFechaEmision(new Date());
        int secuencia = pedidosHoy.size() + 1;

        // Formatear la secuencia a cuatro dígitos
        String secuenciaStr = String.format("%04d", secuencia);

        return "PC-" + currentDate + "-" + secuenciaStr;
    }

}
