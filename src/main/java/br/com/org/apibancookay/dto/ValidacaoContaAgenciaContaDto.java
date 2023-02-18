package br.com.org.apibancookay.dto;

import org.hibernate.validator.constraints.Length;

public class ValidacaoContaAgenciaContaDto {

	@Length(max = 20, message = "Agência maior que 20 digitos")
	private String agencia;
	@Length(max = 20, message = "Conta maior que 20 digitos")
	private String conta;

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

}
