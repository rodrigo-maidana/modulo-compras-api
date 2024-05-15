package modulocompras.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;*/
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration /* extends WebSecurityConfigurerAdapter */ {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * @Override
     * protected void configure(HttpSecurity http) throws Exception {
     * http
     * .csrf().disable() // Deshabilitar CSRF para servicios API
     * .authorizeRequests()
     * .antMatchers("/users/register").permitAll() // Permitir el registro sin
     * autenticación
     * .anyRequest().authenticated() // Todos los demás endpoints requieren
     * autenticación
     * .and()
     * .httpBasic(); // Uso de autenticación básica, podría cambiar a otra forma de
     * autenticación
     * }
     */
}
