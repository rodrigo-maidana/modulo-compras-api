package modulocompras.api.factura;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modulocompras.api.orden_compra.OrdenCompra;
import modulocompras.api.orden_compra.OrdenCompraService;
import modulocompras.api.proveedor.Proveedor;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private OrdenCompraService ordenCompraService;

    public List<Factura> getAllFacturas() {
        return facturaRepository.findByEliminadoFalse();
    }

    public Optional<Factura> getFacturaById(Integer id) {
        return facturaRepository.findByIdAndEliminadoFalse(id);
    }

    public Optional<Factura> updateFactura(Integer id, FacturaDTO facturaDTO) {
        return getFacturaById(id).map(factura -> {
            factura.setEstado(facturaDTO.getEstado());
            factura.setSaldoPendiente(facturaDTO.getSaldoPendiente());
            return Optional.of(facturaRepository.save(factura));
        }).orElse(Optional.empty());
    }

    public Optional<Factura> createFactura(FacturaCreateDTO facturaCreateDTO) {
        OrdenCompra ordenCompra = ordenCompraService.getOrdenCompraById(facturaCreateDTO.getIdOrdenCompra())
                .orElse(null);
        if (ordenCompra == null) {
            return Optional.empty();
        }

        Proveedor proveedor = ordenCompra.getProveedor();

        Factura factura = new Factura(facturaCreateDTO);
        factura.setProveedor(proveedor);
        factura.setOrdenCompra(ordenCompra);

        return Optional.of(facturaRepository.save(factura));
    }

}
