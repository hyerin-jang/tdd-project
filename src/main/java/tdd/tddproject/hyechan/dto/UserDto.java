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
public class UserDto {
    private Long userNo;
    private String userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userYn;

    @Builder
    public UserDto(Long userNo, String userId, String userName, String userEmail, String userPhone, String userYn){
        this.userNo = userNo;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userYn = userYn;
    }

}
