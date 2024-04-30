package modulocompras.api.Marca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    // Aquí puedes añadir métodos personalizados de consulta si es necesario
    
}