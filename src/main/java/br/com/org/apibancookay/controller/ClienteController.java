package br.com.org.apibancookay.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.org.apibancookay.dto.ClienteDto;
import br.com.org.apibancookay.interfaces.ClienteInterface;
import br.com.org.apibancookay.model.Cliente;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

	@Autowired
	private ClienteInterface clienteInterface;

	@GetMapping("/procurarpelocpfesenhadocliente/{pCpf}/{pSenhaCliente}")
	public ResponseEntity<ClienteDto> procurarpeloCpfeSenhaDoCliente(@PathVariable String pCpf,
			@PathVariable String pSenhaCliente) {
		ClienteDto dto = new ClienteDto();
		dto.setCpf(pCpf);
		dto.setSenhaCliente(pSenhaCliente);
		dto.limparErros();
		
		try {
			Map<Integer, String> erros = dto.validacaoProcurarPorCpfSenhaCliente();
			if (!erros.isEmpty())
				return ResponseEntity.status(400).body(dto);

			Cliente resultado = clienteInterface.procurarPorCpfESenhaDoCliente(pCpf, pSenhaCliente);
			if (resultado == null) {
				dto.adicionarErros(2, "Cliente não encotrado");
				return ResponseEntity.status(404).body(dto);
			}

			BeanUtils.copyProperties(resultado, dto);
			return ResponseEntity.status(200).body(dto);
		} catch (Exception e) {
			dto.adicionarErros(3, "Error dentro do servidor");
			return ResponseEntity.status(500).body(dto);
		}
	}

}
