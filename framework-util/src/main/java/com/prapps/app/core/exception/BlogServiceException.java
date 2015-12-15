package com.prapps.app.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="This customer is not found in the system")
public class BlogServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Throwable t;
	
	public BlogServiceException(Throwable t) {
		this.t = t;
	}
}
