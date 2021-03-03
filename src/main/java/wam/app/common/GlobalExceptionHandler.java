package wam.app.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<String> handlerArgumentException(Exception e) {
		logger.debug(HttpStatus.INTERNAL_SERVER_ERROR.value() + " : " + e.getMessage());
		String result =  e.getMessage();
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(result, HttpStatus.INTERNAL_SERVER_ERROR); // 500 반환
		e.printStackTrace();
    	return responseEntity;

	}
}
