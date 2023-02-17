package br.com.org.apibancookay.controller;

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
			contaDto.setResposta("Cliente não encontrado");
			return ResponseEntity.badRequest().body(contaDto);
		} 		
		
		Conta procurarContaId = contaServiceInterface.procurarContaId(procurarClienteCpfSenhaCliente.getId());
		contaDto.setResposta("Conta encontrada");
		BeanUtils.copyProperties(procurarContaId, contaDto);
		return ResponseEntity.ok(contaDto);
	}
	
	@GetMapping("procurar_agencia_conta/{pAgencia}/{pConta}")
	public ResponseEntity<ContaDto> procurarContaAgenciaConta(@PathVariable String pAgencia, @PathVariable String pConta) {
		ContaDto contaDto = new ContaDto();
		contaDto.setAgencia(pAgencia);
		contaDto.setConta(pConta);
		
		String validarProcurarContaAgenciaConta = contaDto.validarProcurarContaAgenciaConta();
		if (!validarProcurarContaAgenciaConta.isEmpty()) {
			contaDto.setResposta(validarProcurarContaAgenciaConta);
			return ResponseEntity.badRequest().body(contaDto);
		}
		
		Conta procurarContaAgenciaConta = contaServiceInterface.procurarContaAgenciaConta(pAgencia, pConta);
		if (procurarContaAgenciaConta == null) {
			contaDto.setResposta("Conta não encontrada");
			return ResponseEntity.badRequest().body(contaDto);
		}
				
		contaDto.setResposta("Conta encontrada");
		BeanUtils.copyProperties(procurarContaAgenciaConta, contaDto);
		return ResponseEntity.ok(contaDto);
	}
	
	@PutMapping("transferir_saldo/{pId1}/{pId2}")
	public ResponseEntity<ContaDto> transferirSaldoConta(@PathVariable Long pId1, @PathVariable Long pId2, @RequestBody ContaDto contaDto) {
		if (!contaDto.validarTransferirSaldoConta().isEmpty()) {
			contaDto.setResposta(contaDto.validarTransferirSaldoConta());
			return ResponseEntity.badRequest().body(contaDto);
		}
		
		contaDto.setResposta("Saldo transferido");
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
