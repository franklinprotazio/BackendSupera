package br.com.banco.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.core.entity.Conta;
import br.com.banco.core.entity.Transferencia;
import br.com.banco.core.service.ContaService;
import br.com.banco.core.service.TransferenciaService;
import br.com.banco.integration.repository.ContaRepository;

@Service
public class ContaServiceImpl implements ContaService {
	
	@Autowired
	private TransferenciaService transferenciaService;

	@Autowired
	private ContaRepository contaRepository;

	@Override
	public List<Conta> getContas() {
		return contaRepository.findAll();
	}

	@Override
	public Conta salvarConta(Conta conta) {
		return contaRepository.save(conta);
	}

	@Override
	public void deletarConta(Conta conta) {
			if(!conta.getTransferencias().isEmpty()) {
				// Deletar todas as transferências dessa conta.
				//transferenciaService.deletarTransferenciaPorConta(conta); 				
			}
		contaRepository.delete(conta);
	}

	@Override
	public Conta buscarContaPorId(Long id) {
		
		Conta conta = contaRepository.findById(id).orElse(null);
		
		List<Transferencia> transferencias = transferenciaService.buscarTransferenciaPorConta(conta);
		conta.setTransferencias(transferencias);
			
		return conta;
	}

	@Override
	public void updateConta(Conta conta) {
		contaRepository.save(conta);
		
	}
	
}
