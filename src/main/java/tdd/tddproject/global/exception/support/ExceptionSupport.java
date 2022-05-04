package tdd.tddproject.global.exception.support;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionSupport extends RuntimeException{

    private final HttpStatus httpStatus;
    private final Integer errorCode;
    private final String message;

    public ExceptionSupport(HttpStatus httpStatus, Integer errorCode, String message){
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}
