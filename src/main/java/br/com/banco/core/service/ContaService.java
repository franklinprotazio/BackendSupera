package br.com.banco.core.service;

import java.util.List;

import br.com.banco.v1.dto.ContaDTO;

public interface ContaService {

	List<ContaDTO> getContas();

	ContaDTO salvarConta(ContaDTO contaDTO);
	
	void deletarConta(Long idConta);
	
	ContaDTO buscarContaPorId(Long id);

	ContaDTO updateConta(Long idConta, ContaDTO contaDTO);
	
}
