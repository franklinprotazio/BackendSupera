package br.com.banco.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.banco.core.entity.Conta;
import br.com.banco.core.entity.Transferencia;
import br.com.banco.core.exception.EntidadeNaoEncontradaException;
import br.com.banco.core.service.ContaService;
import br.com.banco.integration.repository.ContaRepository;
import br.com.banco.integration.repository.TransferenciaRepository;
import br.com.banco.v1.dto.ContaDTO;
import br.com.banco.v1.dto.TransferenciaSemContaDTO;

@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ContaDTO> getContas() {
		List<Conta> contas = contaRepository.findAll();
		List<ContaDTO> listaRetornos = new ArrayList<>();

		if (!contas.isEmpty()) {
			for (Conta conta : contas) {
				List<Transferencia> listaTranferencias = transferenciaRepository
						.findTransferenciaByConta(conta.getIdConta());
				List<TransferenciaSemContaDTO> listaTranferenciasDTO = new ArrayList<>(); // Movido para dentro do loop

				for (Transferencia transferencia : listaTranferencias) {
					TransferenciaSemContaDTO tranferenciasDTO = modelMapper.map(transferencia,
							TransferenciaSemContaDTO.class);
					listaTranferenciasDTO.add(tranferenciasDTO);
				}

				ContaDTO contaDTO = modelMapper.map(conta, ContaDTO.class);
				contaDTO.setTransferencias(listaTranferenciasDTO);
				listaRetornos.add(contaDTO);
			}
		}
		return listaRetornos;
	}

	@Override
	public ContaDTO salvarConta(ContaDTO contaDTO) {

		Conta conta = modelMapper.map(contaDTO, Conta.class);
		ContaDTO contaRetornoDTO = modelMapper.map(contaRepository.save(conta), ContaDTO.class);

		return contaRetornoDTO;
	}

	@Transactional
	@Override
	public void deletarConta(Long idConta) throws EntidadeNaoEncontradaException {

		Conta contaRetorno = contaRepository.findById(idConta).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Não foi encontrado um recurso com o ID: " + idConta));

		transferenciaRepository.deletarTransferenciaPorConta(contaRetorno.getIdConta());
		contaRepository.deleteById(contaRetorno.getIdConta());
	}

	
	@Override
	public ContaDTO buscarContaPorId(Long id) throws EntidadeNaoEncontradaException {
	    Conta conta = contaRepository.findById(id)
	            .orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi encontrado um recurso com o ID: " + id));

	    ContaDTO contaDTO = modelMapper.map(conta, ContaDTO.class);

	    // Buscar as transferências associadas à conta
	    List<Transferencia> transferencias = transferenciaRepository.findTransferenciaByConta(id);
	    List<TransferenciaSemContaDTO> transferenciasDTO = new ArrayList<>();

	    // Mapear as transferências para DTO
	    for (Transferencia transferencia : transferencias) {
	        TransferenciaSemContaDTO transferenciaDTO = modelMapper.map(transferencia, TransferenciaSemContaDTO.class);
	        transferenciasDTO.add(transferenciaDTO);
	    }

	    // Definir as transferências no DTO da conta
	    contaDTO.setTransferencias(transferenciasDTO);

	    return contaDTO;
	}


	@Transactional
	@Override
	public ContaDTO updateConta(Long idConta, ContaDTO contaDTO) throws EntidadeNaoEncontradaException {

		Conta contaRetorno = contaRepository.findById(idConta).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Não foi encontrado um recurso com o ID: " + idConta));

		contaRetorno.setNomeResponsavel(contaDTO.getNomeResponsavel());
		ContaDTO contaRetornoDTO = modelMapper.map(contaRepository.save(contaRetorno), ContaDTO.class);

		return contaRetornoDTO;
	}

}
