package com.enfec.sb.qrcode.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author VenkataManikanta
 */

@ControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

//	this method is used for handling the MethodArgumentNotValidException class
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse errorsResponse = new ErrorResponse();

		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String errorMessage = error.getDefaultMessage();

			errorsResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
			errorsResponse.setErrorMessage(errorMessage);
			

		});

		return new ResponseEntity<>(errorsResponse, HttpStatus.BAD_REQUEST);

	}

	// this method is used for handling the DataIntegrityViolationException class
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<Object> handleDataIntegrityException(DataIntegrityViolationException ex,
			WebRequest request) {

		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(ex.getLocalizedMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

//	this method is used for handling the SQLTransientException class
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setErrorMessage(ex.getLocalizedMessage());
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
