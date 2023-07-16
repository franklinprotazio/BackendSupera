package br.com.banco.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContaTransferenciaDTO {

	private Long idConta;

	private String nomeResponsavel;

}
