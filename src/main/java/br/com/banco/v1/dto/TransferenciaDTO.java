package br.com.banco.v1.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.banco.core.entity.Conta;

public class TransferenciaDTO {

	private Long id;
	
	private Date dataTransferencia;
	
	private BigDecimal valor;
	
	private String tipo;
	
	private String nomeOperadorTransacao;
	
	private Conta conta;
	
}
