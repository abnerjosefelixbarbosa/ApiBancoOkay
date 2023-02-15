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
import br.com.org.apibancookay.model.Cliente;
import br.com.org.apibancookay.model.Conta;
import br.com.org.apibancookay.service.ClienteService;
import br.com.org.apibancookay.service.ContaService;

@RestController
@RequestMapping("/conta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {

	@Autowired
	private ContaService contaService;
	@Autowired
	private ClienteService clienteService;

	@GetMapping("logar/{pCpf}/{pSenhaCliente}")
	public ResponseEntity<ContaDto> logarContaCpfSenhaCliente(@PathVariable String pCpf, @PathVariable String pSenhaCliente) {
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setCpf(pCpf);
		clienteDto.setSenhaCliente(pSenhaCliente);
		ContaDto contaDto = new ContaDto();
		
		if (!clienteDto.validarLogin().isEmpty()) {
			contaDto.setResposta(clienteDto.validarLogin());
			return ResponseEntity.badRequest().body(contaDto);
		}
		
		Cliente procurarClienteCpfSenhaCliente = clienteService.procurarClienteCpfSenhaCliente(pCpf, pSenhaCliente);
		if (procurarClienteCpfSenhaCliente == null) {
			contaDto.setResposta("Cliente não existe");
			return ResponseEntity.badRequest().body(contaDto);
		} 		
		
		Conta procurarContaId = contaService.procurarContaId(procurarClienteCpfSenhaCliente.getId());
		contaDto.setResposta("Login com sucesso");
		BeanUtils.copyProperties(procurarContaId, contaDto);
		return ResponseEntity.ok(contaDto);
	}
}
