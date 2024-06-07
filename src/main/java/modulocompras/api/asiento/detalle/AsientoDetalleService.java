package modulocompras.api.asiento.detalle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsientoDetalleService {

    private final AsientoDetalleRepository asientoDetalleRepository;

    @Autowired
    public AsientoDetalleService(AsientoDetalleRepository asientoDetalleRepository) {
        this.asientoDetalleRepository = asientoDetalleRepository;
    }

    public List<AsientoDetalle> getAllAsientoDetalles() {
        return asientoDetalleRepository.findByEliminadoFalse();
    }

    public Optional<AsientoDetalle> getAsientoDetalleById(Integer id) {
        return asientoDetalleRepository.findByIdAndEliminadoFalse(id);
    }

    public AsientoDetalle saveAsientoDetalle(AsientoDetalle asientoDetalle) {
        return asientoDetalleRepository.save(asientoDetalle);
    }

    public void deleteAsientoDetalle(Integer id) {
        Optional<AsientoDetalle> asientoDetalleOptional = asientoDetalleRepository.findById(id);
        asientoDetalleOptional.ifPresent(asientoDetalle -> {
            asientoDetalle.setEliminado(true);
            asientoDetalleRepository.save(asientoDetalle);
        });
    }
}
