package br.com.org.apibancookay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.org.apibancookay.interfaces.ContaServiceInterface;
import br.com.org.apibancookay.model.Conta;
import br.com.org.apibancookay.repository.ContaReposirory;

@Service
public class ContaService implements ContaServiceInterface {
	
	@Autowired
	private ContaReposirory contaReposirory;

	@Override
	public Conta procurarContaId(Long pId) {
		try {
			return contaReposirory.findById(pId).get();
		} catch (Exception e) {
			return null;
		}		
	}
	
}
