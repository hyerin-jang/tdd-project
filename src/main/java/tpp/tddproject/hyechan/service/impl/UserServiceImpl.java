package tpp.tddproject.hyechan.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tpp.tddproject.domain.entity.user.QUser;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.hyechan.repository.UserRepository;
import tpp.tddproject.hyechan.service.UserService;
import tpp.tddproject.vo.user.UserVO;

import static tpp.tddproject.domain.entity.user.QUser.user;

/**
 * fileName    : UserServiceImpl
 * author      : hyechan
 * date        : 2022/04/09
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/04/09 3:45 오후  hyechan        최초 생성
 */
@Service
@Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void add(UserVO userVO) {

        User user = User.builder()
                .userId(userVO.getUserId())
                .userEmail(userVO.getUserEmail())
                .userName(userVO.getUserName())
                .userPhone(userVO.getUserPhone())
                .userPw(bCryptPasswordEncoder.encode(userVO.getUserPw()))
                .build();
        //Model Mapper 성능이슈로 하나하나 매핑

        userRepository.save(user);
    }
}
