package br.com.org.apibancookay.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.org.apibancookay.dto.ClienteDto;
import br.com.org.apibancookay.dto.ContaDto;
import br.com.org.apibancookay.interfaces.ClienteInterface;
import br.com.org.apibancookay.interfaces.ContaInterface;
import br.com.org.apibancookay.model.Cliente;
import br.com.org.apibancookay.model.Conta;

@RestController
@RequestMapping("/conta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {

	@Autowired
	private ContaInterface contaInterface;
	@Autowired
	private ClienteInterface clienteInterface;
	
	@GetMapping("/logarpelocpfsenhadocliente/{pCpf}/{pSenhaCliente}")
	public ResponseEntity<ContaDto> logarPeloCpfSenhaDoCliente(@PathVariable String pCpf,
			@PathVariable String pSenhaCliente) {
		ContaDto contaDto = new ContaDto();
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setCpf(pCpf);
		clienteDto.setSenhaCliente(pSenhaCliente);
		
		try {
			String erro = clienteDto.validacaoProcurarPorCpfSenhaCliente();
			if (!erro.isEmpty()) {
				contaDto.setErro(erro);
				return ResponseEntity.status(400).body(contaDto);
			}
			
			Cliente clienteProcurado = clienteInterface.procurarPorCpfSenhaDoCliente(pCpf, pSenhaCliente);
			if (clienteProcurado == null) {
				contaDto.setErro("cliente não encontrado");
				return ResponseEntity.status(404).body(contaDto);
			}
			
			Conta contaProcurada = contaInterface.procurarPeloId(clienteProcurado.getId());
			if (contaProcurada == null) {
				contaDto.setErro("conta não encontrada");
				return ResponseEntity.status(404).body(contaDto);
			}	
			
			BeanUtils.copyProperties(contaProcurada, contaDto);
			return ResponseEntity.status(200).body(contaDto);
		}catch (Exception e) {
			contaDto.setErro("erro no servidor");
			return ResponseEntity.status(500).body(contaDto);
		}
	}

	@PutMapping("/sacar/{pId}")
	public ResponseEntity<ContaDto> sacar(@PathVariable Long pId, @RequestBody ContaDto dto) {
		dto.setErro("");
		
		try {			
			String erro = dto.validacaoAlterar();
			if (!erro.isEmpty()) {
				return ResponseEntity.status(400).body(dto);			
			}
			
			Conta resultado = contaInterface.procurarPeloId(pId);
			if (resultado == null) {
				dto.setErro("conta não encontrada");
				return ResponseEntity.status(404).body(dto);
			}
			
			resultado.sacar(dto.getSaldo());
			Conta contaAlterada = contaInterface.alterar(resultado);
			BeanUtils.copyProperties(contaAlterada, dto);
			return ResponseEntity.status(200).body(dto);
		} catch (Exception e) {
			dto.setErro("erro no servidor");
			return ResponseEntity.status(500).body(dto);
		}
	}

	@PutMapping("/depositar/{pId}")
	public ResponseEntity<ContaDto> depositar(@PathVariable Long pId, @RequestBody ContaDto dto) {		
		dto.setErro("");
		
		try {
			String erro = dto.validacaoAlterar();
			if (!erro.isEmpty()) {
				return ResponseEntity.status(400).body(dto);			
			}
			
			Conta resultado = contaInterface.procurarPeloId(pId);
			if (resultado == null) {
				dto.setErro("conta não encontrada");
				return ResponseEntity.status(404).body(dto);
			}
			
			resultado.depositar(dto.getSaldo());
			Conta contaAlterada = contaInterface.alterar(resultado);
			BeanUtils.copyProperties(contaAlterada, dto);
			return ResponseEntity.status(200).body(dto);
		} catch (Exception e) {
			dto.setErro("erro no servidor");
			return ResponseEntity.status(500).body(dto);
		}
	}

}
