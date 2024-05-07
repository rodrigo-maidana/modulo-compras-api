package modulocompras.api.Categoria;

import java.util.List;

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
}
