package modulocompras.api.categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    /**
     * Encuentra todas las categorías que no están marcadas como eliminadas.
     *
     * @return una lista de categorías que no están marcadas como eliminadas
     */
    List<Categoria> findByEliminadoFalse();

    /**
     * Busca una categoría por su ID y verifica que no haya sido eliminada.
     *
     * @param id el ID de la categoría a buscar
     * @return un Optional que contiene la Categoría si se encuentra y no ha sido
     *         eliminada, o vacío si no se encuentra o ha sido eliminada.
     */
    Optional<Categoria> findByIdAndEliminadoFalse(Integer id);
}
