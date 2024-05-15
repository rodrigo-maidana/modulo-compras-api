package modulocompras.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Intenta registrar un nuevo usuario
            userService.registerNewUser(user);
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (IllegalStateException e) {
            // Manejo de excepción si el nombre de usuario ya existe
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Manejo de excepción genérica por otros errores posibles durante el registro
            return ResponseEntity.badRequest().body("Error al registrar el usuario");
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
