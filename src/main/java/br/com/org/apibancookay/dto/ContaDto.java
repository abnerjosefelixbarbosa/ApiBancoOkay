package br.com.org.apibancookay.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
	private Map<Integer, String> erros = new HashMap<>();	
	
	public void limparErros() {
		erros.clear();
	}
	
	public void adicionarErros(Integer chave, String valor) {
		erros.put(chave, valor);
	}
	
	public Map<Integer, String> validacaoAlterar() {	
		limparErros();		
		if (saldo.doubleValue() == 0) {
			adicionarErros(1, "Valor nulo");
		}
		
		return erros;		
	}
	
}
