package br.com.org.apibancookay.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSenhaCliente() {
		return senhaCliente;
	}

	public void setSenhaCliente(String senhaCliente) {
		this.senhaCliente = senhaCliente;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
