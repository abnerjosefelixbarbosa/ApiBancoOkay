package br.com.org.apibancookay.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class ValidacaoClienteCpfSenhaClienteDto {

	@CPF(message = "CPF invalivado")
	private String cpf;
	@Length(min = 6, message = "Senha menor que digitos 6")
	private String senhaCliente;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenhaCliente() {
		return senhaCliente;
	}

	public void setSenhaCliente(String senhaCliente) {
		this.senhaCliente = senhaCliente;
	}

}
