package br.com.banco.v1;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.core.entity.Transferencia;
import br.com.banco.core.service.TransferenciaService;
import br.com.banco.integration.repository.TransferenciaRepository;
import br.com.banco.v1.dto.TransferenciaDTO;

@RestController
@RequestMapping(value = "/v1")
public class TransferenciaController {
	
	@Autowired
	TransferenciaService service;

	@Autowired
	TransferenciaRepository transferenciaRepository;
	
	@Autowired
	private ModelMapper modelMapper; 
	
	@GetMapping("transferencia")
	public List<TransferenciaDTO> getTranferencia() {
		
		List<Transferencia> transferencias = new ArrayList<>();
		List<TransferenciaDTO> transferenciasDTO = new ArrayList<>();
		
		transferencias = service.getTransferencia();
		
		for (Transferencia transferencia : transferencias) {
			TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
			transferenciaDTO = modelMapper.map(transferencia, TransferenciaDTO.class);
			transferenciasDTO.add(transferenciaDTO);
		}
		
		return transferenciasDTO;
	}
	
	@PostMapping("transferencia")
	public ResponseEntity<TransferenciaDTO> saveConta(@RequestBody @Valid TransferenciaDTO transferenciaDTO) {
		
		Transferencia transferencia = new Transferencia();
		transferencia = modelMapper.map(transferenciaDTO, Transferencia.class);	
		transferencia = service.salvarTransferencia(transferencia);
		
		TransferenciaDTO transferenciaDTORetorno = modelMapper.map(transferencia, TransferenciaDTO.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(transferenciaDTORetorno);
	}
}
