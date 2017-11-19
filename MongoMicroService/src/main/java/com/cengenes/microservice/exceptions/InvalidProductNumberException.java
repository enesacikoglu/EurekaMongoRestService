package com.cengenes.microservice.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidProductNumberException extends DuplicateKeyException {

	private static final long serialVersionUID = 1L;

	public InvalidProductNumberException(String msg) {
		super(msg);

	}

}
