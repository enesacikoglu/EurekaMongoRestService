package com.cengenes.microservice.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateNumberException extends DuplicateKeyException {

	private static final long serialVersionUID = 1L;

	public DuplicateNumberException(String msg) {
		super(msg);

	}


}
