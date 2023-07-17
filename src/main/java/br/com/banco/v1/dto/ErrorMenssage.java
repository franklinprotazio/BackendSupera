package br.com.banco.v1.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMenssage {

	private Date dataAtual;
	private String mensagem;

}
