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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
