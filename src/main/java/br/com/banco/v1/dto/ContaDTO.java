package br.com.banco.v1.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContaDTO {

	private Long idConta;

	@NotBlank
	private String nomeResponsavel;

	private List<TransferenciaSemContaDTO> transferencias;

}
