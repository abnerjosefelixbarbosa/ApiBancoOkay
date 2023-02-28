package br.com.org.apibancookay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.org.apibancookay.interfaces.ContaInterface;
import br.com.org.apibancookay.model.Conta;
import br.com.org.apibancookay.repository.ContaReposirory;

@Service
public class ContaService implements ContaInterface {

	@Autowired
	private ContaReposirory contaReposirory;

	@Override
	public Conta procurarPeloId(Long pId) {
		if (!contaReposirory.existsById(pId))
			return null;

		return contaReposirory.findById(pId).get();
	}

	@Override
	public Conta procurarPelaAgenciaConta(String pAgencia, String pConta) {
		if (!contaReposirory.existsByAgenciaAndConta(pAgencia, pConta))
			return null;

		return contaReposirory.findByAgenciaAndConta(pAgencia, pConta).get();
	}

	@Override
	public Conta alterar(Conta conta) {
		return contaReposirory.save(conta);
	}

}
