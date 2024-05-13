package modulocompras.api.marca;

import java.util.List;
import java.util.Optional;

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

    /**
     * Busca una marca por su ID y verifica que no haya sido eliminada.
     * 
     * @param id el ID de la marca a buscar
     * @return un Optional que contiene la Marca si se encuentra y no ha sido
     *         eliminada, o vacío si no se encuentra o ha sido eliminada.
     */
    Optional<Marca> findByIdAndEliminadoFalse(Integer id);
}