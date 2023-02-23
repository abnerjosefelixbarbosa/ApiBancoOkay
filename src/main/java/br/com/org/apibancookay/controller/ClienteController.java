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
import br.com.org.apibancookay.interfaces.ClienteInterface;
import br.com.org.apibancookay.model.Cliente;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

	@Autowired
	private ClienteInterface clienteInterface;

	@GetMapping("/procurarcpfsenhacliente/{pCpf}/{pSenhaCliente}")
	public ResponseEntity<ClienteDto> procurarCpfSenhaCliente(@PathVariable String pCpf,
			@PathVariable String pSenhaCliente) {
		ClienteDto clienteDto = new ClienteDto();
		Cliente resultado = clienteInterface.procurarCpfSenhaCliente(pCpf, pSenhaCliente);

		if (resultado == null)
			return ResponseEntity.notFound().build();

		BeanUtils.copyProperties(resultado, clienteDto);
		return ResponseEntity.ok(clienteDto);
	}

}
