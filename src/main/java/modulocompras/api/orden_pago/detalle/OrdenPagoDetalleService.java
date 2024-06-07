package modulocompras.api.orden_pago.detalle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modulocompras.api.metodo_pago.MetodoPago;
import modulocompras.api.metodo_pago.MetodoPagoService;
import modulocompras.api.orden_pago.OrdenPago;
import modulocompras.api.orden_pago.OrdenPagoRepository;
import modulocompras.api.orden_pago.OrdenPagoService;

@Service
public class OrdenPagoDetalleService {

    @Autowired
    private OrdenPagoDetalleRepository ordenPagoDetalleRepository;

    @Autowired
    private OrdenPagoRepository ordenPagoRepository;

    @Autowired
    private OrdenPagoService ordenPagoService;

    @Autowired
    private MetodoPagoService metodoPagoService;

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

        MetodoPago metodoPago = metodoPagoService.getMetodoPagoById(ordenPagoDetalleDTO.getMetodoPago().getId())
                .orElse(null);
        if (metodoPago == null)
            return Optional.empty();

        Double montoTotal = ordenPago.getMontoTotal();
        Double montoDetalle = ordenPagoDetalleDTO.getMonto();

        if (montoTotal < montoDetalle + montoTotal)
            return Optional.empty();

        ordenPago.setMontoTotal(montoTotal + montoDetalle);
        ordenPagoRepository.save(ordenPago);

        OrdenPagoDetalle newOrdenPagoDetalle = new OrdenPagoDetalle(ordenPagoDetalleDTO);
        newOrdenPagoDetalle.setOrdenPago(ordenPago);

        return Optional.of(ordenPagoDetalleRepository.save(newOrdenPagoDetalle));
    }

    public List<OrdenPagoDetalle> createOrdenPagoDetallesBulk(Integer idOrdenPago,
            List<OrdenPagoDetalleDTO> ordenPagoDetallesDTO) {
        OrdenPago ordenPago = ordenPagoService.getOrdenPagoById(idOrdenPago).orElse(null);
        if (ordenPago == null)
            return null;

        Double montoTotal = ordenPago.getMontoTotal();
        Double montoDetalles = ordenPagoDetallesDTO.stream().mapToDouble(OrdenPagoDetalleDTO::getMonto).sum();
        if (montoTotal < montoDetalles + montoTotal)
            return null;

        ordenPago.setMontoTotal(montoTotal + montoDetalles);
        ordenPagoRepository.save(ordenPago);

        for (OrdenPagoDetalleDTO ordenPagoDetalleDTO : ordenPagoDetallesDTO) {
            MetodoPago metodoPago = metodoPagoService.getMetodoPagoById(ordenPagoDetalleDTO.getMetodoPago().getId())
                    .orElse(null);
            if (metodoPago == null)
                return null;
            OrdenPagoDetalle newOrdenPagoDetalle = new OrdenPagoDetalle(ordenPagoDetalleDTO);
            newOrdenPagoDetalle.setOrdenPago(ordenPago);
            newOrdenPagoDetalle.setMetodoPago(metodoPago);
            ordenPagoDetalleRepository.save(newOrdenPagoDetalle);
        }

        return ordenPagoDetalleRepository.findByOrdenPagoIdAndEliminadoFalse(idOrdenPago);

    }

}
