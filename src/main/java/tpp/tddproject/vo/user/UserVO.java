package tpp.tddproject.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * fileName    : JoinVO
 * author      : hyechan
 * date        : 2022/04/09
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/04/09 3:50 오후  hyechan        최초 생성
 */
@Slf4j
@Data
@AllArgsConstructor
public class UserVO {

    private String userId;

    private String userPw;

    private String userName;

    private String userEmail;

    private String userPhone;
}
