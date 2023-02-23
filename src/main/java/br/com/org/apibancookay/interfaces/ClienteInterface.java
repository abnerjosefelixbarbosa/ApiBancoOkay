package br.com.org.apibancookay.interfaces;

import br.com.org.apibancookay.model.Cliente;

public interface ClienteInterface {
	Cliente procurarCpfSenhaCliente(String pCpf, String pSenhaCliente);
}
