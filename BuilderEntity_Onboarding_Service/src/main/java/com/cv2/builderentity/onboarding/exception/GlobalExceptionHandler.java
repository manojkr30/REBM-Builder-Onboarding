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
	
    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<Object> globalInvalidStateException(InvalidStateException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("Error Message", exception.errormessage);
        errorResponse.put("Error Code", exception.errorStatusCode); 
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> globalEntityNotFoundException(EntityNotFoundException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("Error Message", exception.errormessage);
        errorResponse.put("Error Code",exception.errorStatusCode); 
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

