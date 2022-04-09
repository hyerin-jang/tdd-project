package tpp.tddproject.hyechan.controller;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.hyechan.repository.UserRepository;
import tpp.tddproject.vo.user.UserVO;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
//@RequiredArgsConstructor
class UserControllerTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserController userController;

    // private final UserRepository userRepository;
    // private final UserController userController;
    // maybe 컴포넌트 스캔 문제로 다른 폴더 트리에 있어서 안 될 것이다.

    @Test
    public void addUser() throws Exception{
        //given
        UserVO userVO =
                new UserVO("dhgpcks","123123","햇찬",
                        "dhgpcks@gmail","010-1111-1111");
        //when
        userController.addUser(userVO);
        // 통합테스트로 Repository 사용해서 넣어준다.
        // Controller 따로 단위 테스트를 만들까? ( -> Mock 객체 사용 )
        // Service 따로 단위 테스트

        //then
        List<User> list = userRepository.findAll();
        assertThat(list.size()).isEqualTo(1);
        assertThat(list).extracting("userId").containsExactly("dhgpcks");
    }
}