package br.com.org.apibancookay.controller;

import java.util.Collection;

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

	@GetMapping("/procurarporcpfesenhadocliente/{pCpf}/{pSenhaCliente}")
	public ResponseEntity<ClienteDto> procurarPorCpfESenhaDoCliente(@PathVariable String pCpf,
			@PathVariable String pSenhaCliente) {
		ClienteDto dto = new ClienteDto();
		dto.setCpf(pCpf);
		dto.setSenhaCliente(pSenhaCliente);
		dto.limparErros();

		Collection<String> erros = dto.validacaoProcurarPorCpfSenhaCliente();
		if (!erros.isEmpty())
			return ResponseEntity.badRequest().body(dto);

		Cliente resultado = clienteInterface.procurarPorCpfESenhaDoCliente(pCpf, pSenhaCliente);
		if (resultado == null) {
			dto.adicionarErros("Cliente não encotrado");
			return ResponseEntity.badRequest().body(dto);
		}

		BeanUtils.copyProperties(resultado, dto);
		return ResponseEntity.ok(dto);
	}

}
