package com.fitness.userservice.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fitness.userservice.exception.ApiError;
import com.fitness.userservice.exception.EmailAlreadyExistsException;

@RestController
@RestControllerAdvice
public class GlobalExceptionHandler {

	// This class can be used to handle global exceptions for the UserService
	// application.
	// You can define methods to handle specific exceptions and return appropriate
	// responses.
	// For example, you can handle EmailAlreadyExistsException or any other custom
	// exceptions here.

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ApiError> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
		ApiError apiError = new ApiError(400, "Email already exists", LocalDateTime.now());
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}

}
