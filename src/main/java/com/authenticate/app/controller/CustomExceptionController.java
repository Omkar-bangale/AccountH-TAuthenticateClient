package com.authenticate.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.authenticate.app.filter.TokenValidationFailedException;


@ControllerAdvice
public class CustomExceptionController  {

	@ExceptionHandler(value=TokenValidationFailedException.class)
	public ResponseEntity<Object> exception(TokenValidationFailedException validationfailed)
	{
		return new ResponseEntity<>(TokenValidationFailedException.message, HttpStatus.UNAUTHORIZED);
		
	}
}
