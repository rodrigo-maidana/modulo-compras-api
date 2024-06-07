package modulocompras.api.metodo_pago;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPago> getAllMetodosPago() {
        return metodoPagoRepository.findByEliminadoFalse();
    }

    public Optional<MetodoPago> getMetodoPagoById(Integer id) {
        return metodoPagoRepository.findByIdAndEliminadoFalse(id);
    }

    public Optional<MetodoPago> createMetodoPago(MetodoPagoDTO metodoPagoDTO) {
        MetodoPago newMetodoPago = new MetodoPago(metodoPagoDTO);
        return Optional.of(metodoPagoRepository.save(newMetodoPago));
    }

}
