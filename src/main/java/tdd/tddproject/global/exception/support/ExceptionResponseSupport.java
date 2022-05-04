package tdd.tddproject.global.exception.support;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExceptionResponseSupport {
    private Integer errorCode;
    private String message;

    @Builder
    public ExceptionResponseSupport(Integer errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }
}
