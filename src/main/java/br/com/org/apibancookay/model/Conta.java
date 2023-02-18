package br.com.org.apibancookay.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta", indexes = { @Index(columnList = "agencia"), @Index(columnList = "conta") })
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	@Column(length = 20, nullable = false)
	private String agencia;
	@Column(length = 20, nullable = false, unique = true)
	private String conta;
	@Column(scale = 2, nullable = false)
	private BigDecimal saldo;
	@Column(length = 4, nullable = false, unique = true)
	private String senhaConta;
	@OneToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false, unique = true)
	private Cliente cliente;
	
	public void depositar(BigDecimal saldo) {
		this.saldo = this.saldo.add(saldo);
	}
	
    public void sacar(BigDecimal saldo) {
    	this.saldo = this.saldo.subtract(saldo);
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
}
