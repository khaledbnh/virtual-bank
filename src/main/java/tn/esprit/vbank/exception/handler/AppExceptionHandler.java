package tn.esprit.vbank.exception.handler;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tn.esprit.vbank.exception.shared.ErrorMessage;
import tn.esprit.vbank.exception.EntityAlreadyExistsException;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .code(404)
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> HandleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

	@ExceptionHandler(value = {EntityAlreadyExistsException.class})
	public ResponseEntity<Object> entityNotFoundException(EntityAlreadyExistsException  ex){
		 ErrorMessage errorMessage = ErrorMessage.builder()
	                .message(ex.getMessage())
	                .timestamp(new Date())
	                .code(409)
	                .build();
	        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
	}
}
