package com.radar.solidario.exception.foodStamp.alreadyDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.radar.solidario.util.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class FoodStampAlreadyDateExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(FoodStampAlreadyDateException.class)
	public final ResponseEntity<Object> exceptionHandler(FoodStampAlreadyDateException exception) {
		log.error("CharityAlreadyExistsException - Message: {}", exception);

		Response<Void> response = new Response<>();
		response.addError(exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
