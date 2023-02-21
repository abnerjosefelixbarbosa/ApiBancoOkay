package br.com.org.apibancookay.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.org.apibancookay.dto.ContaDto;
import br.com.org.apibancookay.dto.ValidacaoClienteCpfSenhaClienteDto;
import br.com.org.apibancookay.dto.ValidacaoContaAgenciaContaDto;
import br.com.org.apibancookay.dto.ValidacaoContaSaldoDto;
import br.com.org.apibancookay.interfaces.ClienteServiceInterface;
import br.com.org.apibancookay.interfaces.ContaServiceInterface;
import br.com.org.apibancookay.model.Cliente;
import br.com.org.apibancookay.model.Conta;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/conta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {

	@Autowired
	private ContaServiceInterface contaServiceInterface;
	@Autowired
	private ClienteServiceInterface clienteServiceInterface;
	
	@GetMapping("procurar_cpf_senha_cliente")
	public ResponseEntity<ContaDto> procurarContaCpfSenhaCliente(@Valid @RequestBody ValidacaoClienteCpfSenhaClienteDto validacaoClienteCpfSenhaClienteDto) {
		ContaDto contaDto = new ContaDto();
		
		String cpf = validacaoClienteCpfSenhaClienteDto.getCpf();
		String senhaCliente = validacaoClienteCpfSenhaClienteDto.getSenhaCliente();
		Cliente procurarClienteCpfSenhaCliente = clienteServiceInterface.procurarClienteCpfSenhaCliente(cpf, senhaCliente);
		if (procurarClienteCpfSenhaCliente == null) {
			return ResponseEntity.notFound().build();
		} 		
		
		Conta procurarContaId = contaServiceInterface.procurarContaId(procurarClienteCpfSenhaCliente.getId());
		BeanUtils.copyProperties(procurarContaId, contaDto);
		return ResponseEntity.ok(contaDto);
	}
	
	@GetMapping("procurar_agencia_conta")
	public ResponseEntity<ContaDto> procurarContaAgenciaConta(@Valid @RequestBody ValidacaoContaAgenciaContaDto validacaoContaAgenciaContaDto) {
		ContaDto contaDto = new ContaDto();
		
		String agencia = validacaoContaAgenciaContaDto.getAgencia();
		String conta = validacaoContaAgenciaContaDto.getConta();
		Conta procurarContaAgenciaConta = contaServiceInterface.procurarContaAgenciaConta(agencia, conta);
		if (procurarContaAgenciaConta == null) {
			return ResponseEntity.notFound().build();
		}
				
		BeanUtils.copyProperties(procurarContaAgenciaConta, contaDto);
		return ResponseEntity.ok(contaDto);
	}
	
	@PutMapping("transferir_saldo/{pIdLogada}/{pIdProcurada}")
	public ResponseEntity<ContaDto> transferirSaldoConta(@PathVariable Long pIdLogada, @PathVariable Long pIdProcurada, @Valid @RequestBody ValidacaoContaSaldoDto validacaoContaSaldoDto) {
		ContaDto contaDto = new ContaDto();
		
		if (pIdLogada == pIdProcurada) {
			return ResponseEntity.badRequest().body(contaDto);
		}
		
		Conta procurarContaIdLogada = contaServiceInterface.procurarContaId(pIdLogada);
		if (procurarContaIdLogada == null) {
			return ResponseEntity.notFound().build();
		}
		
		Conta procurarContaIdProcurada = contaServiceInterface.procurarContaId(pIdProcurada);
		if (procurarContaIdProcurada == null) {
			return ResponseEntity.notFound().build();
		}
		
		BigDecimal saldo = validacaoContaSaldoDto.getSaldo();
		procurarContaIdLogada.sacar(saldo);
		procurarContaIdProcurada.depositar(saldo);
		Conta contaLogada = contaServiceInterface.alterarConta(procurarContaIdLogada);
		contaServiceInterface.alterarConta(procurarContaIdProcurada);
		BeanUtils.copyProperties(contaLogada, contaDto);
		return ResponseEntity.ok(contaDto);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
	    Map<String, String> errors = new HashMap<>();
	    e.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    
	    return errors;
	}
	
}
