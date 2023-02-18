package br.com.org.apibancookay.dto;

import java.math.BigDecimal;

import br.com.org.apibancookay.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDto {
	
	private Long id;
	private String agencia;
	private String conta;
	private BigDecimal saldo;
	private String senhaConta;
	private Cliente cliente;
	private String resposta;
	
}
