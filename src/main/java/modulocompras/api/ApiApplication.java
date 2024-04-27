package modulocompras.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Todos los paths
						.allowedOriginPatterns("*") // Permite cualquier origen con un patrón
						.allowedMethods("*") // Permite todos los métodos HTTP
						.allowedHeaders("*") // Permite todas las cabeceras
						.allowCredentials(true); // Permite credenciales
			}
		};
	}

}
