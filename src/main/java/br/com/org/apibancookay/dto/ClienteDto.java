package br.com.org.apibancookay.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
