package tdd.tddproject.global.exception;

import org.springframework.http.HttpStatus;
import tdd.tddproject.global.exception.support.ExceptionSupport;

public class IdNotFoundException extends ExceptionSupport {

    // REST 에선 URI 가 자원이기 때문에 경로가 곧 자원이다.
    // 출처: https://sanghaklee.tistory.com/61 [이상학의 개발블로그]
    // 엔티티 id를 찾지 못할 때 사용하는 Exception
    public IdNotFoundException(ErrorCode errorCode){
        super(errorCode.getStatus(), errorCode.getErrorCode(), errorCode.getMessage());
    }
}
