package jsp.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jsp.springboot.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleNotFound(ResourceNotFoundException ex) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(ex.getMessage());
		response.setData("Failure");
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ResponseStructure<String>> handleDuplicate(DuplicateResourceException ex) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(ex.getMessage());
		response.setData("Failure");
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidOperationException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidOperation(InvalidOperationException ex) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(ex.getMessage());
		response.setData("Failure");
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFound(IdNotFoundException ex) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(ex.getMessage());
		response.setData("Failure");
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidEnum(
	        HttpMessageNotReadableException ex) {

	    String message = "Invalid value provided. "
	            + "Allowed values for AppointmentStatus are: BOOKED, CANCELLED, COMPLETED";

	    ResponseStructure<String> response = new ResponseStructure<>();
	    response.setStatusCode(HttpStatus.BAD_REQUEST.value());
	    response.setMessage(message);
	    response.setData(null);

	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
