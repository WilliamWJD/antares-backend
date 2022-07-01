package com.antares.exceptions;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.antares.services.exceptions.DataIntegrityViolationException;
import com.antares.services.exceptions.ObjectNotFoundException;
import com.antares.services.exceptions.ValidationException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErrorMessage> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request){
		ErrorMessage err = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis(), e.getCause().toString());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorMessage> validationException(Exception e, HttpServletRequest request){
		ErrorMessage err = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis(), e.getCause().toString());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorMessage> dataIntegrityViolationException(Exception e, HttpServletRequest request){
		ErrorMessage err = new ErrorMessage(HttpStatus.CONFLICT.value(), e.getMessage(), System.currentTimeMillis(), e.getCause().toString());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
}
