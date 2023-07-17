package br.com.banco.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.banco.core.entity.Conta;
import br.com.banco.core.entity.Transferencia;
import br.com.banco.core.exception.EntidadeNaoEncontradaException;
import br.com.banco.core.service.TransferenciaService;
import br.com.banco.integration.repository.TransferenciaRepository;
import br.com.banco.v1.dto.ContaRetornoDTO;
import br.com.banco.v1.dto.TransferenciaDTO;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public void deletarTransferenciaPorConta(Conta conta) {
		transferenciaRepository.deletarTransferenciaPorConta(conta.getIdConta());

	}

	@Override
	public List<TransferenciaDTO> buscarTransferenciaPorConta(Conta conta) {

		List<Transferencia> transferencias = transferenciaRepository.findTransferenciaByConta(conta.getIdConta());
		List<TransferenciaDTO> transferenciasDTO = new ArrayList();

		for (Transferencia transferencia : transferencias) {
			TransferenciaDTO transferenciaDTO = modelMapper.map(transferencia, TransferenciaDTO.class);
			transferenciasDTO.add(transferenciaDTO);
		}

		return transferenciasDTO;
	}

	@Transactional
	@Override
	public TransferenciaDTO salvarTransferencia(TransferenciaDTO transferenciaDTO) {

		Transferencia transferencia = modelMapper.map(transferenciaDTO, Transferencia.class);
		TransferenciaDTO transferenciaRetornoDTO = modelMapper.map(transferenciaRepository.save(transferencia),
				TransferenciaDTO.class);
		return transferenciaRetornoDTO;
	}

	@Override
	public TransferenciaDTO buscarTransferenciaPorId(Long idTransferencia) throws EntidadeNaoEncontradaException {

		Transferencia transferencia = transferenciaRepository.findById(idTransferencia).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Não foi encontrado um recurso com o ID: " + idTransferencia));
		TransferenciaDTO transferenciaDTO = modelMapper.map(transferencia, TransferenciaDTO.class);
		return transferenciaDTO;
	}

	@Override
	public void deletarTransferenciaPorId(Long idTransferencia) {

		transferenciaRepository.deleteById(idTransferencia);

	}

	@Override
	public List<TransferenciaDTO> buscarTransferenciasPorNomeOperador(String nomeOperador) {
		List<Transferencia> transferencias = transferenciaRepository.findByNomeOperadorTransacao(nomeOperador);
		List<TransferenciaDTO> transferenciasDTO = new ArrayList();

		for (Transferencia transferencia : transferencias) {

			TransferenciaDTO transferenciaDTO = convertter(transferencia);
			transferenciasDTO.add(transferenciaDTO);
		}

		return transferenciasDTO;
	}

	private TransferenciaDTO convertter(Transferencia transferencia) {
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();

		transferenciaDTO.setDataTransferencia(transferencia.getDataTransferencia());
		transferenciaDTO.setId(transferencia.getId());
		transferenciaDTO.setNomeOperadorTransacao(transferencia.getNomeOperadorTransacao());
		transferenciaDTO.setTipo(transferencia.getTipo());
		transferenciaDTO.setValor(transferencia.getValor());
		ContaRetornoDTO contaRetornoDTO = modelMapper.map(transferencia.getConta(), ContaRetornoDTO.class);
		transferenciaDTO.setConta(contaRetornoDTO);
		return transferenciaDTO;
	}

	@Override
	public List<TransferenciaDTO> getTransferencias(String dataInicio, String dataFim, String nome) {

		dataInicio = Objects.nonNull(dataInicio) ? dataInicio : "";
		dataFim = Objects.nonNull(dataFim) ? dataFim : "";
		nome = Objects.nonNull(nome) ? nome : "";

		List<Transferencia> transferencias = transferenciaRepository.getTransferencias(dataInicio, dataFim, nome);
		List<TransferenciaDTO> transferenciasDTO = new ArrayList<>();

		for (Transferencia transferencia : transferencias) {
			TransferenciaDTO dto = modelMapper.map(transferencia, TransferenciaDTO.class);
			transferenciasDTO.add(dto);
		}

		return transferenciasDTO;
	}

}
