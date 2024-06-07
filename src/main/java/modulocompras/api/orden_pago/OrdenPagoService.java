package modulocompras.api.orden_pago;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import modulocompras.api.factura.Factura;
import modulocompras.api.factura.FacturaService;

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

        return Optional.of(ordenPagoRepository.save(ordenPago));
    }

}
