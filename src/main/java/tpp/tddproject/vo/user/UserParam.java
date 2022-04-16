package tpp.tddproject.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
}
