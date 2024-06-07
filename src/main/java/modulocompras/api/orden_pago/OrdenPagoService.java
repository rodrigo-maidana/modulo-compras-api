package modulocompras.api.orden_pago;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import modulocompras.api.asiento.Asiento;
import modulocompras.api.asiento.AsientoService;
import modulocompras.api.asiento.detalle.AsientoDetalle;
import modulocompras.api.asiento.detalle.AsientoDetalleService;
import modulocompras.api.factura.Factura;
import modulocompras.api.factura.FacturaService;
import modulocompras.api.orden_pago.detalle.OrdenPagoDetalle;
import modulocompras.api.orden_pago.detalle.OrdenPagoDetalleService;

@Service
public class OrdenPagoService {

    @Autowired
    private OrdenPagoRepository ordenPagoRepository;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    @Lazy
    private OrdenPagoDetalleService ordenPagoDetalleService;

    @Autowired
    private AsientoService asientoService;

    @Autowired
    private AsientoDetalleService asientoDetalleService;

    // Listar todas las órdenes de pago
    public List<OrdenPago> getAllOrdenesPago() {
        return ordenPagoRepository.findByEliminadoFalse();
    }

    // Obtener una orden de pago por ID
    public Optional<OrdenPago> getOrdenPagoById(Integer id) {
        return ordenPagoRepository.findByIdAndEliminadoFalse(id);
    }

    // Crear una nueva orden de pago
    public Optional<OrdenPago> createOrdenPago(Integer idFactura, OrdenPagoDTO ordenPagoDTO) {
        Factura factura = facturaService.getFacturaById(idFactura).orElse(null);
        if (factura == null)
            return Optional.empty();

        OrdenPago ordenPago = new OrdenPago(ordenPagoDTO);
        ordenPago.setFactura(factura);
        ordenPago.setEstado("Pendiente");
        ordenPago.setNroOrdenPago(generateNroOrdenCompra());
        ordenPago.setMontoTotal(0.0);

        return Optional.of(ordenPagoRepository.save(ordenPago));
    }

    public OrdenPagoDTO getOrdenPagoPreview() {
        OrdenPagoDTO ordenPagoDTO = new OrdenPagoDTO();
        ordenPagoDTO.setNroOrdenPago(generateNroOrdenCompra());
        ordenPagoDTO.setFechaPago(new Date());
        return ordenPagoDTO;

    }

    // Generar el número de pedido
    private String generateNroOrdenCompra() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String currentDate = sdf.format(new Date());

        Date today = new Date(new java.util.Date().getTime());

        // Contar los pedidos de hoy
        List<OrdenPago> pedidosHoy = ordenPagoRepository.findByfechaPago(today);

        int secuencia = pedidosHoy.size() + 1;

        // Formatear la secuencia a cuatro dígitos
        String secuenciaStr = String.format("%04d", secuencia);

        return "OP-" + currentDate + "-" + secuenciaStr;
    }

    public Optional<OrdenPago> autorizarOrdenPago(Integer idOrdenPago) {
        OrdenPago ordenPago = getOrdenPagoById(idOrdenPago).orElse(null);
        if (ordenPago == null)
            return Optional.empty();

        // Crear asiento
        Asiento asiento = new Asiento();
        asiento.setFecha(new Date());
        asiento.setDescripcion("Pago de factura " + ordenPago.getFactura().getNroFactura());
        asiento.setTotal(ordenPago.getMontoTotal());
        asientoService.saveAsiento(asiento);

        // Crear detalles de asiento
        crearDetallesDeAsiento(idOrdenPago, asiento, ordenPago.getMontoTotal());

        Factura factura = ordenPago.getFactura();
        factura.setSaldoPendiente(factura.getSaldoPendiente() - ordenPago.getMontoTotal());
        if (factura.getSaldoPendiente() == 0.0) {
            factura.setEstado("Pagado");
        }

        facturaService.saveFactura(factura);

        ordenPago.setEstado("Autorizado");
        return Optional.of(ordenPagoRepository.save(ordenPago));
    }

    private void crearDetallesDeAsiento(Integer idOrdenPago, Asiento asiento, double montoTotal) {
        // Crear detalles Debe
        List<OrdenPagoDetalle> detalles = ordenPagoDetalleService.getDetallesByOrdenPagoId(idOrdenPago);
        for (OrdenPagoDetalle detalle : detalles) {
            AsientoDetalle asientoDetalle = new AsientoDetalle();
            asientoDetalle.setAsiento(asiento);
            asientoDetalle.setCuenta(detalle.getMetodoPago().getId().toString());
            asientoDetalle.setDebe(detalle.getMonto());
            asientoDetalle.setHaber(0.0);
            asientoDetalleService.saveAsientoDetalle(asientoDetalle);
        }

        // Crear detalle Haber
        AsientoDetalle asientoDetalle = new AsientoDetalle();
        asientoDetalle.setAsiento(asiento);
        asientoDetalle.setCuenta("1.1.1.1.1.1");
        asientoDetalle.setDebe(0.0);
        asientoDetalle.setHaber(montoTotal);
        asientoDetalleService.saveAsientoDetalle(asientoDetalle);
    }

}
