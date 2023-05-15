package com.radovan.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.radovan.spring.exceptions.DataNotValidatedException;
import com.radovan.spring.exceptions.InstanceNotExistException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(DataNotValidatedException.class)
	public ResponseEntity<String> handleDataNotValidatedException(DataNotValidatedException exc){
		return ResponseEntity.internalServerError().body("Data is not validated!!!");
	}
	
	@ExceptionHandler(InstanceNotExistException.class)
	public ResponseEntity<String> handleInstanceNotExistException(InstanceNotExistException exc){
		return ResponseEntity.internalServerError().body("Instance not found!!!");
	}
}
