package modulocompras.api.asiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modulocompras.api.asiento.detalle.AsientoDetalle;
import modulocompras.api.asiento.detalle.AsientoDetalleRepository;
import modulocompras.api.orden_pago.OrdenPago;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepository;

    @Autowired
    private AsientoDetalleRepository asientoDetalleRepository;

    public AsientoService(AsientoRepository asientoRepository) {
        this.asientoRepository = asientoRepository;
    }

    public List<Asiento> getAllAsientos() {
        return asientoRepository.findByEliminadoFalse();
    }

    public Optional<Asiento> getAsientoById(Integer id) {
        return asientoRepository.findByIdAndEliminadoFalse(id);
    }

    public Asiento saveAsiento(Asiento asiento) {
        return asientoRepository.save(asiento);
    }

    public void deleteAsiento(Integer id) {
        Optional<Asiento> asientoOptional = asientoRepository.findById(id);
        asientoOptional.ifPresent(asiento -> {
            asiento.setEliminado(true);
            asientoRepository.save(asiento);
        });
    }

    public Asiento crearAsientoPorOrdenDePago(OrdenPago ordenPago) {
        // Crear el asiento principal
        Asiento asiento = new Asiento();
        asiento.setFecha(new Date());

        // Crear detalles del asiento
        AsientoDetalle detalleDebe = new AsientoDetalle();
        detalleDebe.setAsiento(asiento);
        detalleDebe.setCuenta("1.1.1.1"); // Efectivo
        detalleDebe.setDebe(ordenPago.getMontoTotal());
        detalleDebe.setHaber(0.0);

        // Guardar el detalle
        asientoDetalleRepository.save(detalleDebe);

        AsientoDetalle detalleHaber = new AsientoDetalle();
        detalleHaber.setAsiento(asiento);
        detalleHaber.setCuenta("1.1.1.2"); // Tarjetas
        detalleHaber.setDebe(0.0);
        detalleHaber.setHaber(ordenPago.getMontoTotal());

        // Guardar el detalle
        asientoDetalleRepository.save(detalleHaber);

        // Guardar el asiento y los detalles
        asientoRepository.save(asiento);

        return asiento;
    }
}
