package modulocompras.api.asiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsientoService {

    private final AsientoRepository asientoRepository;

    @Autowired
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
}
