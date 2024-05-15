package modulocompras.api.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     * 
     * @param username el nombre de usuario a buscar
     * @return el usuario encontrado o null si no se encuentra ninguno
     */
    User findByUsername(String username);
}
