package com.cv2.builderentity.onboarding.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BuilderNotFoundException.class)
	public ResponseEntity<Object> globalBuilderNotFoundNotException(BuilderNotFoundException exception){
		Map<String,String> errorResponse= new HashMap<>();
		errorResponse.put("Eroor Message", exception.errormessage);
		errorResponse.put("ERROR Code", exception.errorStatusCode);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
}
