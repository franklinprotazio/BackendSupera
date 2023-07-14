package br.com.banco.core.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.core.entity.Conta;
import br.com.banco.core.entity.Transferencia;
import br.com.banco.core.service.TransferenciaService;
import br.com.banco.integration.repository.TransferenciaRepository;

@Service
public class TransferenciaServiceImpl implements TransferenciaService{
	
	@Autowired
	private TransferenciaRepository transferenciaRepository;
	
	@Override
	public List<Transferencia> getTransferencia() {
		return transferenciaRepository.findAll();
		
	}

	@Transactional
	@Override
	public void deletarTransferenciaPorConta(Conta conta) {
		transferenciaRepository.deletarTransferenciaPorConta(conta.getIdConta());
		
	}
	
	@Override
	public List<Transferencia> buscarTransferenciaPorConta(Conta conta) {
		return transferenciaRepository.findTransferenciaByConta(conta.getIdConta());
//		return transferenciaRepository.findAll();

	}

	@Override
	public Transferencia salvarTransferencia(Transferencia transferencia) {
		return transferenciaRepository.save(transferencia);
	}

}
