package br.com.banco.core.service;

import java.util.List;

import br.com.banco.core.entity.Conta;
import br.com.banco.core.entity.Transferencia;

public interface TransferenciaService {

	List<Transferencia> getTransferencia();

	void deletarTransferenciaPorConta(Conta conta);

	List<Transferencia> buscarTransferenciaPorConta(Conta conta);

	Transferencia salvarTransferencia(Transferencia transferencia);

	Transferencia buscarTransferenciaPorId(Long idTransferencia);

	void deletarTransferenciaPorId(Long idTransferencia);


}
