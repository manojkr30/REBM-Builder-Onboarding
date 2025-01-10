package com.cv2.builderentity.onboarding.exception;

import java.lang.Exception;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({BuilderNotFoundException.class ,BuilderNotFoundException.class})
	public ResponseEntity<Object> globalBuilderNotFoundNotException(BuilderNotFoundException exception) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("Eroor Message", exception.errormessage);
		errorResponse.put("ERROR Code", exception.errorStatusCode);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> globalServiceException(){
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("Eroor Message", "Service unavailable due to technical error.");
		errorResponse.put("ERROR Code", "ONBR_501");
		return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
