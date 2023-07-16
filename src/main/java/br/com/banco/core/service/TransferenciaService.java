package br.com.banco.core.service;

import java.util.List;

import br.com.banco.core.entity.Conta;
import br.com.banco.v1.dto.TransferenciaDTO;

public interface TransferenciaService {

	void deletarTransferenciaPorConta(Conta conta);

	List<TransferenciaDTO> buscarTransferenciaPorConta(Conta conta);

	TransferenciaDTO salvarTransferencia(TransferenciaDTO transferenciaDTO);

	TransferenciaDTO buscarTransferenciaPorId(Long idTransferencia);

	void deletarTransferenciaPorId(Long idTransferencia);

	List<TransferenciaDTO> buscarTransferenciasPorNomeOperador(String nomeOperadorTransacao);

	List<TransferenciaDTO> getTransferencias(String dataInicio, String dataFim, String nome);

}
