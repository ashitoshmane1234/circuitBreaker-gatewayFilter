package com.mindbowser.exception;

import org.springframework.http.HttpStatus;

/**
 * @author mindbowser | secure-gate team
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;

	/**
	 * @author mindbowser | secure-gate team
	 */
	public CustomException(String message, Throwable cause) {
		super(message, cause);
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	/**
	 * @author mindbowser | secure-gate team
	 */
	public CustomException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	/**
	 * @author mindbowser | secure-gate team
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
