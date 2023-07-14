package br.com.banco.v1;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import br.com.banco.core.entity.Conta;
import br.com.banco.core.service.ContaService;
import br.com.banco.v1.dto.ContaDTO;

@RestController
@RequestMapping(value = "/v1/")
public class ContaController {

	@Autowired
	ContaService service;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("conta")
	public List<ContaDTO> getContas() {

		List<Conta> contas = new ArrayList<>();
		List<ContaDTO> contasDTO = new ArrayList<>();

		contas = service.getContas();

		for (Conta conta : contas) {
			ContaDTO contaDTO = new ContaDTO();
			contaDTO = modelMapper.map(conta, ContaDTO.class);
			contasDTO.add(contaDTO);
		}

		return contasDTO;
	}

	@PostMapping("conta")
	public ResponseEntity<ContaDTO> saveConta(@RequestBody @Valid ContaDTO contaDTO) {
		Conta conta = new Conta();
		conta = modelMapper.map(contaDTO, Conta.class);

		conta = service.salvarConta(conta);

		ContaDTO contaDTORetorno = modelMapper.map(conta, ContaDTO.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(contaDTORetorno);
	}

	@DeleteMapping("conta/{idConta}")
	public ResponseEntity<Object> deleteConta(@PathVariable(value = "idConta") Long idConta) {

		Conta conta = service.buscarContaPorId(idConta);
		if (conta != null) {
			service.deletarConta(conta);
			return ResponseEntity.status(HttpStatus.OK).body("Conta deletada com sucesso.");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta com o Id = " + idConta + " não foi encontrada.");
	}

	@GetMapping("conta/{idConta}")
	public ResponseEntity<Object> buscarContaPorId(@PathVariable(value = "idConta") Long idConta) {
		Conta conta = service.buscarContaPorId(idConta);

		if (conta != null) {

			ContaDTO contaDTO = modelMapper.map(conta, ContaDTO.class);
			contaDTO.setTransferencias(conta.getTransferencias());
			return ResponseEntity.status(HttpStatus.OK).body(contaDTO);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta com o Id = " + idConta + " não foi encontrada.");

	}

	@PutMapping("conta/{idConta}")
	public ResponseEntity<Object> updateConta(@RequestBody @Valid ContaDTO contaDTO, @PathVariable(value = "idConta") Long idConta) {

		Conta conta = service.buscarContaPorId(idConta);
		if (conta != null) {
			conta.setNomeResponsavel(conta.getNomeResponsavel());
			conta.setNomeResponsavel(contaDTO.getNomeResponsavel());
			service.updateConta(conta);
			return ResponseEntity.status(HttpStatus.OK).body("Conta atualizada com sucesso.");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta com o Id = " + idConta + " não foi encontrada.");

	}

}
