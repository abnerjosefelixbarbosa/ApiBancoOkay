package br.com.org.apibancookay.dto;

import java.math.BigDecimal;

import br.com.org.apibancookay.model.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContaDto {
	
	private Long id;
	private String agencia;
	private String conta;
	private BigDecimal saldo;
	private String senhaConta;
	private Cliente cliente;
	private String erro;
	
	public String validacaoAlterar() {	
		erro = "";		
		if (saldo.doubleValue() == 0) {
			erro = "saldo nulo";
		}
		
		return erro;		
	}
	
}
