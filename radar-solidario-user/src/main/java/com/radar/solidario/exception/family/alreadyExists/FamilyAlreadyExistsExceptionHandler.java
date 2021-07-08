package com.radar.solidario.exception.family.alreadyExists;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.radar.solidario.util.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class FamilyAlreadyExistsExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(FamilyAlreadyExistsException.class)
	public final ResponseEntity<Object> exceptionHandler(FamilyAlreadyExistsException exception) {
		log.error("FamilyAlreadyExistsException - Message: {}", exception);

		Response<Void> response = new Response<>();
		response.addError(exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
