package net.miaoubich.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler(EmployeeCustomException.class)
	public ResponseEntity<ErrorMessage> employeeExceptionHandler(EmployeeCustomException exception){
		return new ResponseEntity<>(ErrorMessage.builder()
											.errorMessage(exception.getMessage())
											.errorCode(exception.getErrorCode())
											.build(), exception.getStatus());
												
	}
}
