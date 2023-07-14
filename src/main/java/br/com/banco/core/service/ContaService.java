package br.com.banco.core.service;

import java.util.List;

import br.com.banco.core.entity.Conta;

public interface ContaService {

	List<Conta> getContas();

	Conta salvarConta(Conta conta);
	
	void deletarConta(Conta conta);
	
	Conta buscarContaPorId(Long id);

	void updateConta(Conta conta);
	
}
