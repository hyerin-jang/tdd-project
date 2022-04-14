package tpp.tddproject.hyechan.service;

// 단위 테스트 ( Service 관련된 애들만 메모리에 띄움)
// BoardRepository 가짜 객체로  - Mock

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tpp.tddproject.hyechan.repository.UserRepository;

@ExtendWith(MockitoExtension.class) // springboot x, Mockito 따로 띄움
public class UserServiceUnitTest {

    @InjectMocks // UserService 객체 만들 때 @Mock 으로 등록된 모든 것을 주입받는다.
    private UserService userService;

    @Mock
    private UserRepository userRepository;
}
