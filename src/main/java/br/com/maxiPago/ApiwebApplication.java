package br.com.maxiPago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 
 * @author Kevyn miranda dos Santos -
 * 
 * Classe responsavel por dar o start no projeto.
 * 
 * Class responsible for starting the project.
 */
@SpringBootApplication
public class ApiwebApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiwebApplication.class, args);
	}
	
}
