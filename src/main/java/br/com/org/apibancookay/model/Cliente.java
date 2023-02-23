package br.com.org.apibancookay.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cliente", indexes = { @Index(columnList = "cpf"), @Index(columnList = "senhaCliente") })
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	@Column(length = 100, nullable = false)
	private String nome;
	@Column(length = 20, nullable = false, unique = true)
	private String cpf;
	@Column(length = 20, nullable = false, unique = true)
	private String rg;
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private LocalDate dataNascimento;
	@Column(length = 6, nullable = false, unique = true)
	private String senhaCliente;
	@Column(precision = 10, nullable = false)
	private Long numero;
	@Column(length = 20, nullable = false)
	private String cep;
	@Column(length = 50, nullable = false)
	private String logradouro;
	@Column(length = 50, nullable = false)
	private String bairro;
	@Column(length = 50, nullable = false)
	private String cidade;
	@Column(length = 2, nullable = false)
	private String estado;
	
}
