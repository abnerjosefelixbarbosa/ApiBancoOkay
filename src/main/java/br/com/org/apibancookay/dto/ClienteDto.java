package br.com.org.apibancookay.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	private String erro;

	public String validacaoProcurarPorCpfSenhaCliente() {
		String erro = "";
		
		if (!cpfValido()) {
			erro = "cpf invalido";
		}

		return erro;
	}

	public boolean cpfValido() {
		String cpf = this.cpf.replace(".", "").replace("-", "");

		if (cpf == "")
			return false;
		if (cpf.length() != 11)
			return false;
		if (cpf == "00000000000")
			return false;
		if (cpf == "11111111111")
			return false;
		if (cpf == "22222222222")
			return false;
		if (cpf == "33333333333")
			return false;
		if (cpf == "44444444444")
			return false;
		if (cpf == "55555555555")
			return false;
		if (cpf == "66666666666")
			return false;
		if (cpf == "77777777777")
			return false;
		if (cpf == "88888888888")
			return false;
		if (cpf == "99999999999")
			return false;

		char digito10, digito11;
		int soma, i, resto, numero, peso;

		try {
			soma = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				numero = (int) (cpf.charAt(i) - 48);
				soma = soma + (numero * peso);
				peso = peso - 1;
			}

			resto = 11 - (soma % 11);
			if (resto == 10 || resto == 11) {
				digito10 = '0';
			} else {
				digito10 = (char) (resto + 48);
			}

			soma = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				numero = (int) (cpf.charAt(i) - 48);
				soma = soma + (numero * peso);
				peso = peso - 1;
			}

			resto = 11 - (soma % 11);
			if (resto == 10 || resto == 11) {
				digito11 = '0';
			} else {
				digito11 = (char) (resto + 48);
			}
			
			if (digito10 != cpf.charAt(9) || digito11 != cpf.charAt(10)) {
				return false;
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
