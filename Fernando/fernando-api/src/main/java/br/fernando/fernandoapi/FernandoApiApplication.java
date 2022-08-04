package br.fernando.fernandoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "br.fernando.fernandoapi" })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class FernandoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FernandoApiApplication.class, args);
	}

}
