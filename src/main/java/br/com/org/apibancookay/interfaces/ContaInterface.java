package br.com.org.apibancookay.interfaces;

import br.com.org.apibancookay.model.Conta;

public interface ContaInterface {
	Conta procurarPeloId(Long pId);
	Conta procurarPelaAgenciaeConta(String pAgencia, String pConta);
	Conta alterar(Conta conta);
}
