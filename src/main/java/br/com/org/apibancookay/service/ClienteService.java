package br.com.org.apibancookay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.org.apibancookay.interfaces.ClienteServiceInterface;
import br.com.org.apibancookay.model.Cliente;
import br.com.org.apibancookay.repository.ClienteRepository;

@Service
public class ClienteService implements ClienteServiceInterface {	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Cliente procurarClienteCpfSenhaCliente(String pCpf, String pSenhaCliente) {
		try {
			if (clienteRepository.existsByCpfAndSenhaCliente(pCpf, pSenhaCliente) == false) {
				return null;
			}
			
			return clienteRepository.findByCpfAndSenhaCliente(pCpf, pSenhaCliente).get();
		} catch (Exception e) {
			return null;
		}
	}
	
}
