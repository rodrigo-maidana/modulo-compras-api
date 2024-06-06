package modulocompras.api.depositos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository depositoRepository;

    public List<DepositoDTO> getAllDepositos() {
        return depositoRepository.findByEliminadoFalse().stream()
                .map(deposito -> new DepositoDTO(deposito))
                .collect(Collectors.toList());
    }

    public Optional<Deposito> getDepositoById(Integer id) {
        return depositoRepository.findById(id);
    }

    public DepositoDTO createDeposito(DepositoDTO depositoDTO) {
        Deposito newDeposito = new Deposito();
        newDeposito.setNombre(depositoDTO.getNombre());
        newDeposito.setDireccion(depositoDTO.getDireccion());
        newDeposito.setContacto(depositoDTO.getContacto());
        Deposito savedDeposito = depositoRepository.save(newDeposito);
        return new DepositoDTO(savedDeposito);
    }

    public Optional<DepositoDTO> updateDeposito(Integer id, DepositoDTO depositoDTO) {
        return depositoRepository.findById(id)
                .filter(deposito -> !deposito.getEliminado())
                .map(existingDeposito -> {
                    existingDeposito.setNombre(depositoDTO.getNombre());
                    existingDeposito.setDireccion(depositoDTO.getDireccion());
                    existingDeposito.setContacto(depositoDTO.getContacto());
                    Deposito updatedDeposito = depositoRepository.save(existingDeposito);
                    return new DepositoDTO(updatedDeposito);
                });
    }

    public boolean deleteDeposito(Integer id) {
        return depositoRepository.findById(id)
                .map(deposito -> {
                    deposito.setEliminado(true);
                    depositoRepository.save(deposito);
                    return true;
                }).orElse(false);
    }
}
