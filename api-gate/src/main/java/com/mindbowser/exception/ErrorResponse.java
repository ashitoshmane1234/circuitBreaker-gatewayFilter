package com.mindbowser.exception;

import java.io.Serializable;

/**
 * @author mindbowser | secure-gate team
 */
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String path;

	private Integer status;

	private String error;

	private String requestId;

	public void setPath(String path) {
		this.path = path;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getPath() {
		return path;
	}

	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getRequestId() {
		return requestId;
	}

}
