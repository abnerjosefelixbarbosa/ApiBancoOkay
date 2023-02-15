package br.com.org.apibancookay.dto;

import java.time.LocalDate;

public class ClienteDto {

	private Long id;
	private String nome;
	private String cpf;
	private String rg;
	private String email;
	private LocalDate dataNascimento;
	private String senhaCliente;
	private Long numero;
	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;

	public String validarLogin() {
		if (!validarCpf().equals("CPF valido")) {
			return validarCpf();
		}
		if (senhaCliente.length() < 6) {
			return "Senha deve ter 6 digitos";
		}
		
		return "";
	}

	public String validarCpf() {
		String expressao = cpf.replace(".", "").replace("-", "");
		if (expressao.equals("00000000000") || expressao.equals("11111111111") || expressao.equals("22222222222")
				|| expressao.equals("33333333333") || expressao.equals("44444444444") || expressao.equals("55555555555")
				|| expressao.equals("66666666666") || expressao.equals("77777777777") || expressao.equals("88888888888")
				|| expressao.equals("99999999999") || (expressao.length() != 11)) {
			return "CPF invalido";
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (expressao.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + 48);
			}

			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (expressao.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';
			} else {
				dig11 = (char) (r + 48);
			}

			if ((dig10 == expressao.charAt(9)) && (dig11 == expressao.charAt(10))) {
				return "CPF valido";
			} else {
				return "CPF invalido";
			}
		} catch (Exception e) {
			return "CPF invalido";
		}
	}

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
