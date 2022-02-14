package io.javatask.appcrudprogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AppCrudProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppCrudProgramApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*")
				.allowedOrigins("*")
				.allowedMethods("GET", "PUT", "DELETE","POST")
				.allowCredentials(false).maxAge(3600);;
			}
		};
	}

}
