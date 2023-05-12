package com.info.exception;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorCode> handleUserNotFOundException(UserNotFoundException ex) {
		ErrorCode errorCode = new ErrorCode();
		errorCode.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorCode.setErrorMessage(ex.getLocalizedMessage());
		return new ResponseEntity<ErrorCode>(errorCode, HttpStatus.OK);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("status", status.BAD_REQUEST);
		List<String> errors = new ArrayList<>();
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldError = result.getFieldErrors();
		for(FieldError f: fieldError) {
			errors.add(f.getDefaultMessage());
		}
		body.put("errors",errors );
		return new ResponseEntity<>(body,status);
	}

}
