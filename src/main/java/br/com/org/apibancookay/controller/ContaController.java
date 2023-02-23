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
import br.com.org.apibancookay.interfaces.ContaInterface;
import br.com.org.apibancookay.model.Conta;

@RestController
@RequestMapping("/conta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {

	@Autowired
	private ContaInterface contaInterface;

	@GetMapping("/procurarid/{pId}")
	public ResponseEntity<ContaDto> procurarId(@PathVariable Long pId) {
		ContaDto contaDto = new ContaDto();
		Conta resultado = contaInterface.procurarId(pId);

		if (resultado == null)
			return ResponseEntity.notFound().build();

		BeanUtils.copyProperties(resultado, contaDto);
		return ResponseEntity.ok(contaDto);
	}

	@GetMapping("/procuraragenciaconta/{pAgencia}/{pConta}")
	public ResponseEntity<ContaDto> procurarAgenciaConta(@PathVariable String pAgencia, @PathVariable String pConta) {
		ContaDto contaDto = new ContaDto();
		Conta resultado = contaInterface.procurarAgenciaConta(pAgencia, pConta);

		if (resultado == null)
			return ResponseEntity.notFound().build();

		BeanUtils.copyProperties(resultado, contaDto);
		return ResponseEntity.ok(contaDto);
	}

	@PutMapping("/sacar/{pId}")
	public ResponseEntity<ContaDto> sacar(@PathVariable Long pId, @RequestBody ContaDto contaDto) {
		Conta resultado = contaInterface.procurarId(pId);

		if (resultado == null)
			return ResponseEntity.notFound().build();

		resultado.sacar(contaDto.getSaldo());
		Conta contaAlterada = contaInterface.alterar(resultado);
		BeanUtils.copyProperties(contaAlterada, contaDto);
		return ResponseEntity.ok(contaDto);
	}

	@PutMapping("/depositar/{pId}")
	public ResponseEntity<ContaDto> depositar(@PathVariable Long pId, @RequestBody ContaDto contaDto) {
		Conta resultado = contaInterface.procurarId(pId);

		if (resultado == null)
			return ResponseEntity.notFound().build();

		resultado.depositar(contaDto.getSaldo());
		Conta contaAlterada = contaInterface.alterar(resultado);
		BeanUtils.copyProperties(contaAlterada, contaDto);
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
