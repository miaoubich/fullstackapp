package net.miaoubich.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class EmployeeCustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private HttpStatus status;
	
	public EmployeeCustomException(String message, String errorCode, HttpStatus status) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}
}
