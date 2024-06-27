package modulocompras.api.cuenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta getCuentaById(Integer id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    public Cuenta saveCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public void deleteCuenta(Integer id) {
        cuentaRepository.deleteById(id);
    }

    // Método para obtener una cuenta por numero de cuenta
    public Cuenta getCuentaByNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta).orElse(null);
    }
}
