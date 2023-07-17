package br.com.banco.v1.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferenciaDTO {

	private Long id;

	private Date dataTransferencia;

	// Pesquisar uma anotação que permita apenas dois valores após a casa decimal
	@Digits(integer = 10, fraction = 2)
	@DecimalMax(value = "9999999999.99")
	private BigDecimal valor;

	private String tipo;

	private String nomeOperadorTransacao;

	private ContaRetornoDTO conta;

}
