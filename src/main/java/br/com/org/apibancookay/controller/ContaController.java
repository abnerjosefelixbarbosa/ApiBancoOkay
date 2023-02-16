package br.com.org.apibancookay.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.org.apibancookay.dto.ClienteDto;
import br.com.org.apibancookay.dto.ContaDto;
import br.com.org.apibancookay.interfaces.ClienteServiceInterface;
import br.com.org.apibancookay.interfaces.ContaServiceInterface;
import br.com.org.apibancookay.model.Cliente;
import br.com.org.apibancookay.model.Conta;

@RestController
@RequestMapping("/conta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {

	@Autowired
	private ContaServiceInterface contaServiceInterface;
	@Autowired
	private ClienteServiceInterface clienteServiceInterface;

	@GetMapping("procurar_cpf_senha_cliente/{pCpf}/{pSenhaCliente}")
	public ResponseEntity<ContaDto> procurarContaCpfSenhaCliente(@PathVariable String pCpf, @PathVariable String pSenhaCliente) {
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setCpf(pCpf);
		clienteDto.setSenhaCliente(pSenhaCliente);
		ContaDto contaDto = new ContaDto();
		
		String validarProcurarContaCpfSenhaCliente = clienteDto.validarProcurarContaCpfSenhaCliente();
		if (!validarProcurarContaCpfSenhaCliente.isEmpty()) {
			contaDto.setResposta(validarProcurarContaCpfSenhaCliente);
			return ResponseEntity.badRequest().body(contaDto);
		}
		
		Cliente procurarClienteCpfSenhaCliente = clienteServiceInterface.procurarClienteCpfSenhaCliente(pCpf, pSenhaCliente);
		if (procurarClienteCpfSenhaCliente == null) {
			contaDto.setResposta("Cliente não existe");
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
		
		contaDto.setResposta("Conta encontrada");
		return ResponseEntity.ok(contaDto);
	}
	
}
