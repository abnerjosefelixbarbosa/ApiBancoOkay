package br.com.org.apibancookay.dto;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidacaoContaAgenciaContaDto {

	@Length(max = 20, message = "Agência maior que 20 digitos")
	private String agencia;
	@Length(max = 20, message = "Conta maior que 20 digitos")
	private String conta;

}
