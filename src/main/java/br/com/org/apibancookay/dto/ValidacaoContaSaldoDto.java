package br.com.org.apibancookay.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class ValidacaoContaSaldoDto {

	@NotNull(message = "Saldo nulo")
	@DecimalMin(value = "0.01", message = "Saldo menor que 0.01")
	private BigDecimal saldo;

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
