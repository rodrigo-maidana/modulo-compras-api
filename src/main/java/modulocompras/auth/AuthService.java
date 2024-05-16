package modulocompras.auth;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import modulocompras.jwt.JwtService;
import modulocompras.user.Role;
import modulocompras.user.User;
import modulocompras.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

}
