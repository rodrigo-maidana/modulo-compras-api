package modulocompras.api.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    // Aquí puedes añadir métodos personalizados de consulta si es necesario
    
}
