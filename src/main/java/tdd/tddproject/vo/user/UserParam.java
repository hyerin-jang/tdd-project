package tdd.tddproject.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tdd.tddproject.domain.entity.user.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * fileName    : UserParam
 * author      : hyechan
 * date        : 2022/04/09
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/04/09 3:50 오후  hyechan        최초 생성
 * 2022/04/14 19:00 오후 hyechan        vo -> parameter 용 dto
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParam {

    private String userId;

    private String userPw;

    private String userName;

    private String userEmail;

    private String userPhone;

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userName(userName)
                .userEmail(userEmail)
                .userPhone(userPhone)
                .userPw(userPw).build();
    }

}
