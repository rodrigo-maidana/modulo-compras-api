package modulocompras.api.orden_pago.detalle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modulocompras.api.orden_pago.OrdenPago;
import modulocompras.api.orden_pago.OrdenPagoService;

@Service
public class OrdenPagoDetalleService {

    @Autowired
    private OrdenPagoDetalleRepository ordenPagoDetalleRepository;

    @Autowired
    private OrdenPagoService ordenPagoService;

    public List<OrdenPagoDetalle> getAllOrdenesPagoDetalles() {
        return ordenPagoDetalleRepository.findByEliminadoFalse();

    }

    public Optional<OrdenPagoDetalle> getOrdenPagoDetalleById(Integer id) {
        return ordenPagoDetalleRepository.findByIdAndEliminadoFalse(id);
    }

    public Optional<OrdenPagoDetalle> createOrdenPagoDetalle(Integer idOrdenPago,
            OrdenPagoDetalleDTO ordenPagoDetalleDTO) {
        OrdenPago ordenPago = ordenPagoService.getOrdenPagoById(idOrdenPago).orElse(null);
        if (ordenPago == null)
            return Optional.empty();

        OrdenPagoDetalle newOrdenPagoDetalle = new OrdenPagoDetalle(ordenPagoDetalleDTO);
        newOrdenPagoDetalle.setOrdenPago(ordenPago);

        return Optional.of(ordenPagoDetalleRepository.save(newOrdenPagoDetalle));
    }

}
