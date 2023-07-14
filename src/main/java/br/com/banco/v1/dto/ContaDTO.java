package br.com.banco.v1.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.banco.core.entity.Transferencia;
import lombok.Data;

@Data
public class ContaDTO {

	private Long idConta;
	
	@NotBlank
	private String nomeResponsavel;
	
	private List<Transferencia> transferencias; 
	
}
