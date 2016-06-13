package com.citrix.training.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ApiBaseController {
	
	@ExceptionHandler({DataAccessException.class})
	  public ResponseEntity<ApiError> databaseError(DataAccessException dae) {
	    return new ResponseEntity<ApiError>(new ApiError(HttpStatus.BAD_REQUEST.value(), dae.getMostSpecificCause().getMessage()), HttpStatus.BAD_REQUEST);
	  }
	
	@ExceptionHandler({ConstraintViolationException.class})
	  public ResponseEntity<List<FieldError>> databaseError(ConstraintViolationException cve) {
		List<FieldError> messages = new ArrayList<FieldError>();
		for (ConstraintViolation constraintViolation : cve.getConstraintViolations()) {
			messages.add(new FieldError(WordUtils.uncapitalize(constraintViolation.getRootBeanClass().getSimpleName()), constraintViolation.getPropertyPath().toString(), constraintViolation.getInvalidValue(), false, null, null, constraintViolation.getMessage()));
		}
	    return new ResponseEntity<List<FieldError>>(messages, HttpStatus.BAD_REQUEST);
	  }

	

}
