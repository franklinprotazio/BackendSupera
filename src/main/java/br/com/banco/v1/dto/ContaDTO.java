package br.com.banco.v1.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ContaDTO {

	private Long idConta;
	
	@NotBlank
	private String nomeResponsavel;
}
