package br.com.org.apibancookay.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidacaoClienteCpfSenhaClienteDto {

	@CPF(message = "CPF invalivado")
	private String cpf;
	@Length(min = 6, message = "Senha menor que digitos 6")
	private String senhaCliente;

}
