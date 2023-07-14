package br.com.banco.core.service;

import java.util.List;
import java.util.Optional;

import br.com.banco.core.entity.Conta;

public interface ContaService {

	List<Conta> getContas();

	Conta salvarConta(Conta conta);
	
}
