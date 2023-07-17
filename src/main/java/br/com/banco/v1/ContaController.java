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
import br.com.banco.v1.dto.TransferenciaSemContaDTO;

@RestController
@RequestMapping(value = "/v1/conta")
public class ContaController {

	private static final String MENSAGEM_CONTA_INESISTENTE = "Não foram encontradas não foi encontrado uma conta com o id = ";

	@Autowired
	TransferenciaService transferenciaService;

	@Autowired
	ContaService service;

	@GetMapping()
	public ResponseEntity<Object> getContas() {

		List<ContaDTO> contasDTO = service.getContas();

		if (contasDTO.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(contasDTO);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não foram encontradas contas na base de dados");
	}

	@PostMapping()
	public ResponseEntity<ContaDTO> saveConta(@RequestBody @Valid ContaDTO contaDTO) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarConta(contaDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteConta(@PathVariable(value = "id") Long idConta) {

		service.deletarConta(idConta);

		return ResponseEntity.status(HttpStatus.OK).body("Conta deletada com sucesso");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarContaPorId(@PathVariable(value = "id") Long idConta) {

		ContaDTO contaDTO = service.buscarContaPorId(idConta);
		if (contaDTO != null && contaDTO.getIdConta() != null) {
			return ResponseEntity.status(HttpStatus.OK).body(contaDTO);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(MENSAGEM_CONTA_INESISTENTE + idConta);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateConta(@RequestBody @Valid ContaDTO contaDTO,
			@PathVariable(value = "id") Long idConta) {
		
		ContaDTO contaRetorno = service.updateConta(idConta, contaDTO);

		if (contaRetorno != null && contaRetorno.getIdConta() != null) {
			return ResponseEntity.status(HttpStatus.OK).body(contaRetorno);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(MENSAGEM_CONTA_INESISTENTE);

	}

}
