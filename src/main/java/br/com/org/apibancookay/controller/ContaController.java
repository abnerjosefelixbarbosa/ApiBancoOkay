package br.com.org.apibancookay.controller;

import java.util.Map;

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

import br.com.org.apibancookay.dto.ContaDto;
import br.com.org.apibancookay.interfaces.ContaInterface;
import br.com.org.apibancookay.model.Conta;

@RestController
@RequestMapping("/conta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {

	@Autowired
	private ContaInterface contaInterface;

	@GetMapping("/procurarpeloid/{pId}")
	public ResponseEntity<ContaDto> procurarPeloId(@PathVariable Long pId) {
		ContaDto dto = new ContaDto();
		dto.limparErros();

		try {
			Conta resultado = contaInterface.procurarPeloId(pId);

			if (resultado == null) {
				dto.adicionarErros(4, "Conta não encontrada");
				return ResponseEntity.status(404).body(dto);
			}

			BeanUtils.copyProperties(resultado, dto);
			return ResponseEntity.status(200).body(dto);
		} catch (Exception e) {
			dto.adicionarErros(3, "Error no servidor");
			return ResponseEntity.status(500).body(dto);
		}
	}

	@GetMapping("/procuraragenciaconta/{pAgencia}/{pConta}")
	public ResponseEntity<ContaDto> procurarAgenciaConta(@PathVariable String pAgencia, @PathVariable String pConta) {
		ContaDto dto = new ContaDto();
		
		try {
			Conta resultado = contaInterface.procurarPelaAgenciaConta(pAgencia, pConta);

			if (resultado == null)
				return ResponseEntity.status(404).body(dto);

			BeanUtils.copyProperties(resultado, dto);
			return ResponseEntity.status(200).body(dto);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(dto);
		}
	}

	@PutMapping("/sacar/{pId}")
	public ResponseEntity<ContaDto> sacar(@PathVariable Long pId, @RequestBody ContaDto dto) {
		try {
			Conta resultado = contaInterface.procurarPeloId(pId);
			
			Map<Integer, String> erros = dto.validacaoAlterar();
			if (!erros.isEmpty()) 
				return ResponseEntity.status(400).body(dto);			

			if (resultado == null)
				return ResponseEntity.status(404).body(dto);

			resultado.sacar(dto.getSaldo());
			Conta contaAlterada = contaInterface.alterar(resultado);
			BeanUtils.copyProperties(contaAlterada, dto);
			return ResponseEntity.status(200).body(dto);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(dto);
		}
	}

	@PutMapping("/depositar/{pId}")
	public ResponseEntity<ContaDto> depositar(@PathVariable Long pId, @RequestBody ContaDto dto) {		
		try {
			Conta resultado = contaInterface.procurarPeloId(pId);

			if (resultado == null)
				return ResponseEntity.status(404).body(dto);

			resultado.depositar(dto.getSaldo());
			Conta contaAlterada = contaInterface.alterar(resultado);
			BeanUtils.copyProperties(contaAlterada, dto);
			return ResponseEntity.status(200).body(dto);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(dto);
		}
	}

}
