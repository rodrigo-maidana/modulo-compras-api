package modulocompras.api.orden_pago;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modulocompras.api.factura.Factura;
import modulocompras.api.factura.FacturaService;

@Service
public class OrdenPagoService {

    @Autowired
    private OrdenPagoRepository ordenPagoRepository;

    @Autowired
    private FacturaService facturaService;

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
        ordenPago.setNroOrdenPago(generateNroOrdenCompra());
        ordenPago.setMontoTotal(factura.getMontoTotal());

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

}
