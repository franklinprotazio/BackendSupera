package br.com.banco.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("conta")
	public List<ContaDTO> getContas() {
	
		List<Conta> contas = new ArrayList<>();
		List<ContaDTO> contasDTO = new ArrayList<>();
		
		contas = service.getContas();
		
		for (Conta conta : contas) {
			ContaDTO contaDTO = new ContaDTO();
			contaDTO.setIdConta(conta.getIdConta());
			contaDTO.setNomeResponsavel(conta.getNomeResponsavel());
			contasDTO.add(contaDTO);
		}
		
		return contasDTO;
	}
	
}
