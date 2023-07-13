package br.com.banco.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.core.entity.Conta;
import br.com.banco.core.service.ContaService;
import br.com.banco.integration.repository.ContaRepository;

@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Override
	public List<Conta> getContas() {
		return contaRepository.findAll();
	}	
	
}
