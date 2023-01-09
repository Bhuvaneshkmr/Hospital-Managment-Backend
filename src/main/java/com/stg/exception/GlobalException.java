package com.stg.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author bhuvaneshkumarg
 *
 */
@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(HealthException.class)
	public ResponseEntity<Map<String, Object>> exception(Exception exception) {
		Map<String, Object> mapException = new HashMap<String, Object>();

		mapException.put("TimeStamp", LocalDateTime.now());
		mapException.put("Error", exception.getMessage());

		return new ResponseEntity<Map<String, Object>>(mapException, HttpStatus.CONFLICT);
	}

}
