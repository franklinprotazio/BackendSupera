package br.com.banco.v1;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.core.service.TransferenciaService;
import br.com.banco.integration.repository.TransferenciaRepository;
import br.com.banco.v1.dto.TransferenciaDTO;

@RestController
@RequestMapping(value = "/v1/transferencia")
public class TransferenciaController {

	@Autowired
	TransferenciaService service;

	@Autowired
	TransferenciaRepository transferenciaRepository;

	@GetMapping()
	public ResponseEntity<Object> getTransferencias(
			@RequestParam(name = "dataInicio", required = false) String dataInicio,
			@RequestParam(name = "dataFim", required = false) String dataFim,
			@RequestParam(name = "nome", required = false) String nome) {

		List<TransferenciaDTO> lista = service.getTransferencias(dataInicio, dataFim, nome);

		return ResponseEntity.status(HttpStatus.CREATED).body(lista);

	}

	@PostMapping()
	public ResponseEntity<Object> saveTransferencia(@RequestBody @Valid TransferenciaDTO transferenciaDTO) {

		TransferenciaDTO transferenciaReturnoDTO = service.salvarTransferencia(transferenciaDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(transferenciaReturnoDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTransferencia(@PathVariable(value = "id") Long idTransferencia) {

		service.deletarTransferenciaPorId(idTransferencia);

		return ResponseEntity.status(HttpStatus.OK).body("A transferência de ID "+ idTransferencia +" deletada com sucesso.");
	}

	@GetMapping("nome-operador/{nomeOperador}")
	public ResponseEntity<Object> buscarOperador(@PathVariable(value = "nomeOperador") String nomeOperador) {

		List<TransferenciaDTO> transferenciasDTO = new ArrayList<>();

		transferenciasDTO = service.buscarTransferenciasPorNomeOperador(nomeOperador);

		return ResponseEntity.status(HttpStatus.OK).body(transferenciasDTO);
	}

}
