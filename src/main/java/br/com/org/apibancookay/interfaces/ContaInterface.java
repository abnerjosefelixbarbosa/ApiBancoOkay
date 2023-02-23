package br.com.org.apibancookay.interfaces;

import br.com.org.apibancookay.model.Conta;

public interface ContaInterface {
	Conta procurarId(Long pId);
	Conta procurarAgenciaConta(String pAgencia, String pConta);
	Conta alterar(Conta conta);
}
