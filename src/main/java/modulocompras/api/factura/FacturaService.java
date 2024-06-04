package modulocompras.api.factura;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

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

}
