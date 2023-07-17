package br.com.banco.v1.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferenciaContaDTO {

	private Long id;

	private Date dataTransferencia;

	private BigDecimal valor;

	private String tipo;

	private String nomeOperadorTransacao;

	private ContaTransferenciaDTO conta;

}
