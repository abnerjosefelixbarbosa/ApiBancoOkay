package br.com.org.apibancookay.interfaces;

import br.com.org.apibancookay.model.Cliente;

public interface ClienteInterface {
	Cliente procurarClienteCpfSenhaCliente(String pCpf, String pSenhaCliente);
}
