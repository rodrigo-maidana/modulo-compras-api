package modulocompras.api.asiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import modulocompras.api.asiento.detalle.AsientoDetalle;
import modulocompras.api.asiento.detalle.AsientoDetalleRepository;
import modulocompras.api.cuenta.Cuenta;
import modulocompras.api.cuenta.CuentaService;
import modulocompras.api.orden_pago.OrdenPago;
import modulocompras.api.orden_pago.detalle.OrdenPagoDetalle;
import modulocompras.api.orden_pago.detalle.OrdenPagoDetalleService;
import modulocompras.api.metodo_pago.MetodoPago;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepository;

    @Autowired
    private AsientoDetalleRepository asientoDetalleRepository;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    @Lazy
    private OrdenPagoDetalleService ordenPagoDetalleService;

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

    public Asiento generarAsiento(OrdenPago ordenPago) {
        // Crear el asiento principal
        Asiento asiento = new Asiento();
        asiento.setFecha(new Date());
        asiento.setDescripcion("Pago de factura " + ordenPago.getFactura().getNroFactura());
        asiento.setTotal(ordenPago.getMontoTotal());
        asiento.setEliminado(false);
        asientoRepository.save(asiento);

        // Obtener los detalles de la orden de pago usando el servicio
        List<OrdenPagoDetalle> detalles = ordenPagoDetalleService.getDetallesByOrdenPagoId(ordenPago.getId());

        // Crear los detalles del asiento basados en los métodos de pago
        for (OrdenPagoDetalle detalle : detalles) {
            MetodoPago metodoPago = detalle.getMetodoPago();
            Double monto = detalle.getMonto();

            // Detalle en el debe para el método de pago
            AsientoDetalle debeDetalle = new AsientoDetalle();
            debeDetalle.setAsiento(asiento);
            debeDetalle.setCuenta(metodoPago.getCuenta());
            debeDetalle.setDebe(monto);
            debeDetalle.setHaber(0.0);
            asientoDetalleRepository.save(debeDetalle);
        }

        // Detalle en el haber para Mercaderías
        AsientoDetalle haberDetalle = new AsientoDetalle();
        haberDetalle.setAsiento(asiento);
        haberDetalle.setCuenta(obtenerCuentaPorNumeroCuenta("5110")); // Método para obtener la cuenta "Mercaderías" por
                                                                      // número
        haberDetalle.setDebe(0.0);
        haberDetalle.setHaber(ordenPago.getMontoTotal());
        asientoDetalleRepository.save(haberDetalle);

        return asiento;
    }

    private Cuenta obtenerCuentaPorNumeroCuenta(String numeroCuenta) {
        // Lógica para obtener una cuenta por su número
        return cuentaService.getCuentaByNumeroCuenta(numeroCuenta);
    }
}
