package br.com.org.apibancookay.dto;

import java.math.BigDecimal;

import br.com.org.apibancookay.model.Cliente;

public class ContaDto {
	
	private Long id;
	private String agencia;
	private String conta;
	private BigDecimal saldo;
	private String senhaConta;
	private Cliente cliente;
	private String resposta;
	
	public String validarTransferirSaldoConta() {
		if (saldo == null || saldo.doubleValue() == 0) {
			return "Saldo nulo";
		}
		
		return "";
	}
	
	public String validarProcurarContaAgenciaConta() {
		if (agencia.length() > 20) {
			return "Agência maior que 20 digitos";
		}
		if (conta.length() > 20) {
			return "Conta maior que 20 digitos";
		}
		
		return "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getSenhaConta() {
		return senhaConta;
	}

	public void setSenhaConta(String senhaConta) {
		this.senhaConta = senhaConta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
}
