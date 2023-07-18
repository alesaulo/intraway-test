package com.intraway.exercise.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequest extends ResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6327553601162339818L;

	public BadRequest( String reason) {
		super(HttpStatus.BAD_REQUEST, reason);
	}
}
