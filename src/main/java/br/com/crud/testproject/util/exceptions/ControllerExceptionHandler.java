package br.com.crud.testproject.util.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.crud.testproject.util.Response;

@RestControllerAdvice
public class ControllerExceptionHandler  {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		return ResponseEntity.badRequest().body(getResponseException(new ApiErrors<BindingResult>(bindingResult)));
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusinessException(BusinessException ex) {
		return ResponseEntity.badRequest().body(getResponseException(new ApiErrors<BusinessException>(ex)));
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getResponseException(new ApiErrors<NotFoundException>(ex)));
	}

    private Response<?> getResponseException(ApiErrors<?> apiError) {
		return new Response<>(apiError.getErrors());
	}
}
