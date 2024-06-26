package modulocompras.api.reportes;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import modulocompras.api.factura.FacturaDTO;
import modulocompras.api.factura.FacturaService;
import modulocompras.api.pedido_compra.PedidoCompraDTO;
import modulocompras.api.pedido_compra.PedidoCompraService;

@RestController
@RequestMapping("/api/v1/reportes")
@Tag(name = "Q. Reportes")
public class ReporteController {

    @Autowired
    private PedidoCompraService pedidoCompraService;

    @Autowired
    private FacturaService facturaService;

    // Obtener Pedidos De Compra con estado pendiente
    @GetMapping("/pedidos-compra-pendientes")
    public List<PedidoCompraDTO> getPedidosCompraPendientes() {
        return pedidoCompraService.getPedidosCompraPendientes().stream()
                .map(PedidoCompraDTO::new)
                .collect(Collectors.toList());
    }

    // Devuelve una lista de facturas con vencimiento en el mes actual (fecha de
    // vencimiento <= último día del mes actual)
    @GetMapping("/facturas-vencimiento-mes-actual")
    public List<FacturaDTO> getFacturasVencimientoMesActual() {
        return facturaService.getFacturasVencimientoMesActual().stream()
                .map(FacturaDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene las facturas a pagar de un proveedor específico con vencimiento en el
     * mes actual.
     * 
     * Este método filtra las facturas que pertenecen a un proveedor dado y cuya
     * fecha de vencimiento
     * es menor o igual al último día del mes actual.
     *
     * @param proveedorId El ID del proveedor cuyas facturas se desean obtener.
     * @return Una lista de facturas que cumplen con los criterios de filtro.
     */
    @GetMapping("/facturas-vencimiento-mes-actual/{proveedorId}")
    public List<FacturaDTO> getFacturasVencimientoMesActualPorProveedor(@PathVariable int proveedorId) {
        return facturaService.getFacturasVencimientoMesActualPorProveedor(proveedorId).stream()
                .map(FacturaDTO::new)
                .collect(Collectors.toList());
    }

}
