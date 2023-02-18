package br.com.org.apibancookay.interfaces;

import br.com.org.apibancookay.model.Conta;

public interface ContaInterface {
	Conta procurarContaId(Long pId);
	Conta procurarContaAgenciaConta(String pAgencia, String pConta);
	Conta alterarConta(Conta conta);
}
