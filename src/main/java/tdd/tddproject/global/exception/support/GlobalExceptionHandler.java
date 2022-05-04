package tdd.tddproject.global.exception.support;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExceptionSupport.class)
    public ResponseEntity<?> handler(ExceptionSupport e){
        ExceptionResponseSupport response = ExceptionResponseSupport.builder()
                .message(e.getMessage())
                .errorCode(e.getErrorCode())
                .build();

        return new ResponseEntity<>(response, e.getHttpStatus());
    }
}
