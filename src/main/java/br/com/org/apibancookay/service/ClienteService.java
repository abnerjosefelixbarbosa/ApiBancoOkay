package br.com.org.apibancookay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.org.apibancookay.interfaces.ClienteInterface;
import br.com.org.apibancookay.model.Cliente;
import br.com.org.apibancookay.repository.ClienteRepository;

@Service
public class ClienteService implements ClienteInterface {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente procurarPorCpfSenhaDoCliente(String pCpf, String pSenhaCliente) {
		if (!clienteRepository.existsByCpfAndSenhaCliente(pCpf, pSenhaCliente))
			return null;
		
		return clienteRepository.findByCpfAndSenhaCliente(pCpf, pSenhaCliente).get();
	}

}
