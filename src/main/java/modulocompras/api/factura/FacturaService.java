package modulocompras.api.factura;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modulocompras.api.depositos.Deposito;
import modulocompras.api.depositos.DepositoService;
import modulocompras.api.orden_compra.OrdenCompra;
import modulocompras.api.orden_compra.OrdenCompraRepository;
import modulocompras.api.orden_compra.OrdenCompraService;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private OrdenCompraService ordenCompraService;

    @Autowired
    private DepositoService depositoService;

    // Obtener todas las facturas
    public List<Factura> getAllFacturas() {
        return facturaRepository.findByEliminadoFalse();
    }

    // Obtener una factura por ID
    public Optional<Factura> getFacturaById(Integer id) {
        return facturaRepository.findByIdAndEliminadoFalse(id);
    }

    // Actualizar una factura existente
    public Optional<Factura> updateFactura(Integer id, FacturaDTO facturaDTO) {
        return getFacturaById(id).map(factura -> {
            factura.setEstado(facturaDTO.getEstado());
            factura.setSaldoPendiente(facturaDTO.getSaldoPendiente());
            return Optional.of(facturaRepository.save(factura));
        }).orElse(Optional.empty());
    }

    // Crear una nueva factura
    public Optional<Factura> createFactura(FacturaCreateDTO facturaCreateDTO) {
        OrdenCompra ordenCompra = ordenCompraService.getOrdenCompraById(facturaCreateDTO.getIdOrdenCompra())
                .orElse(null);
        if (ordenCompra == null)
            return Optional.empty();

        Deposito deposito = depositoService.getDepositoById(facturaCreateDTO.getIdDeposito()).orElse(null);
        if (deposito == null)
            return Optional.empty();

        if (!isValidNumeroFactura(facturaCreateDTO.getNroFactura())
                || !isValidCondicion(facturaCreateDTO.getCondicion()))
            return Optional.empty();

        ordenCompra.setEstado("Completado");
        ordenCompraRepository.save(ordenCompra);

        Factura factura = new Factura(facturaCreateDTO);
        factura.setProveedor(ordenCompra.getProveedor());
        factura.setOrdenCompra(ordenCompra);
        factura.setDeposito(deposito);
        factura.setEstado("Pendiente");
        factura.setMontoTotal(0.0);
        factura.setSaldoPendiente(0.0);

        return Optional.of(facturaRepository.save(factura));
    }

    public boolean isValidNumeroFactura(String numeroFactura) {
        // Expresión regular para validar el formato 00X-00X-000000X
        String facturaPattern = "\\d{2}\\d-\\d{2}\\d-\\d{6}\\d";
        return Pattern.matches(facturaPattern, numeroFactura);
    }

    public boolean isValidCondicion(String condicion) {
        return "Contado".equalsIgnoreCase(condicion) || "Credito".equalsIgnoreCase(condicion);
    }

    // Eliminar una factura (soft delete)
    public boolean deletedFactura(Integer id) {
        return getFacturaById(id).map(factura -> {
            factura.setEliminado(true);
            facturaRepository.save(factura);
            return true;
        }).orElse(false);
    }

}
