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
        if (ordenCompra == null) {
            return Optional.empty();
        }

        Proveedor proveedor = ordenCompra.getProveedor();

        Factura factura = new Factura(facturaCreateDTO);
        factura.setProveedor(proveedor);
        factura.setOrdenCompra(ordenCompra);

        return Optional.of(facturaRepository.save(factura));
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
