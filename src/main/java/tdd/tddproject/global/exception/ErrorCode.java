package tdd.tddproject.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {


    //USER
    USER_NOT_EXIST(HttpStatus.NOT_FOUND, 404, "존재하지 않는 사용자입니다"),
    //ADDRESS
    ADDRESS_NOT_EXIST(HttpStatus.NOT_FOUND,404,"존재하지 않는 주소 입니다"),
    //ROLE
    ROLE_NOT_EXIST(HttpStatus.NOT_FOUND,404,"존재하지 않는 ROLE 입니다");


    ErrorCode(HttpStatus status, int errorCode, String message){
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

    private HttpStatus status;
    private Integer errorCode;
    private String message;

    public HttpStatus getStatus(){
        return status;
    }
    public int getErrorCode() {
        return errorCode;
    }
    public String getMessage(){
        return message;
    }
}
