package br.com.banco.core.exception;

import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.banco.v1.dto.ErrorMenssage;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleAneyException(Exception e, WebRequest request) {
		String erroDescricao = e.getLocalizedMessage();
		if (Objects.isNull(erroDescricao)) {
			erroDescricao.toString();
		}
		ErrorMenssage errorMenssage = new ErrorMenssage(new Date(), erroDescricao);
		return new ResponseEntity<>(errorMenssage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
