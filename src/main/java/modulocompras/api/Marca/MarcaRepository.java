package modulocompras.api.Marca;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    /**
     * Obtiene una lista de marcas que no han sido eliminadas.
     * 
     * @return una lista de objetos Marca que no han sido eliminados
     */
    List<Marca> findByEliminadoFalse();
}