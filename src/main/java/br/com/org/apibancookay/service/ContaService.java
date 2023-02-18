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
			if (!contaReposirory.existsById(pId)) {
				return null;
			}
			
			return contaReposirory.findById(pId).get();
		} catch (Exception e) {
			return null;
		}		
	}

	@Override
	public Conta procurarContaAgenciaConta(String pAgencia, String pConta) {
		try {
			if (!contaReposirory.existsByAgenciaAndConta(pAgencia, pConta)) {
				return null;
			}
			
			return contaReposirory.findByAgenciaAndConta(pAgencia, pConta).get();
		} catch (Exception e) {
			return null;
		}		
	}

	@Override
	public Conta alterarConta(Conta conta) {
		return contaReposirory.save(conta);
	}
	
}
