package br.com.org.apibancookay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.org.apibancookay.configuration.DataConfiguration;

@SpringBootApplication
public class ApiBancoOkayApplication implements ApplicationRunner {
	
	@Autowired
	private DataConfiguration dataConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(ApiBancoOkayApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		dataConfiguration.configurar();
	}
	
}
