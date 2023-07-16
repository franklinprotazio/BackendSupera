package br.com.banco.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.core.service.ContaService;
import br.com.banco.core.service.TransferenciaService;
import br.com.banco.v1.dto.ContaDTO;

@RestController
@RequestMapping(value = "/v1/conta")
public class ContaController {

	@Autowired
	TransferenciaService transferenciaService;

	@Autowired
	ContaService service;

	@GetMapping()
	public ResponseEntity<Object> getContas() {
		
		List<ContaDTO> contasDTO = service.getContas();

		return ResponseEntity.status(HttpStatus.OK).body(contasDTO);

	}

	@PostMapping()
	public ResponseEntity<ContaDTO> saveConta(@RequestBody @Valid ContaDTO contaDTO) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarConta(contaDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteConta(@PathVariable(value = "id") Long idConta) {
		
		service.deletarConta(idConta);

		return ResponseEntity.status(HttpStatus.OK).body("A conta de id " + idConta + " foi deletada com sucesso.");
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarContaPorId(@PathVariable(value = "id") Long idConta) {

		ContaDTO contaDTO = service.buscarContaPorId(idConta);

		return ResponseEntity.status(HttpStatus.OK).body(contaDTO);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ContaDTO> updateConta(@RequestBody @Valid ContaDTO contaDTO,
			@PathVariable(value = "id") Long idConta) {

		return ResponseEntity.status(HttpStatus.OK).body(service.updateConta(idConta, contaDTO));

	}

}
