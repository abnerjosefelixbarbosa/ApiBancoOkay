package br.com.org.apibancookay.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.org.apibancookay.interfaces.DataInterface;

@Component
@Profile("dev")
public class DataConfiguration implements DataInterface {
	@Override
	public void configurar() {
		System.out.println("configurações de dev");
	}
}
