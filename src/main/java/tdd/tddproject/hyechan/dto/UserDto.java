package tdd.tddproject.hyechan.dto;

import lombok.Builder;
import lombok.Data;

/**
 * fileName    : UserDto
 * author      : hyechan
 * date        : 2022/04/11
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/04/11 1:36 오후  hyechan        최초 생성
 */
@Data
@Builder
public class UserDto {

    private Long userNo;
    private String userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userYn;
}
