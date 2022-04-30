package tdd.tddproject.hyechan.service;

// 단위 테스트 ( Service 관련된 애들만 메모리에 띄움)
// BoardRepository 가짜 객체로  - Mock
//docall메서드
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tdd.tddproject.domain.entity.user.User;
import tdd.tddproject.hyechan.dto.UserDto;
import tdd.tddproject.hyechan.repository.UserRepository;
import tdd.tddproject.hyechan.util.UserConstructor;
import tdd.tddproject.vo.user.UserParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // springboot x, Mockito 따로 띄움
public class UserServiceUnitTest extends UserConstructor {

    @InjectMocks // UserService 객체 만들 때 @Mock 으로 등록된 모든 것을 주입받는다.
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    // Mock 을 사용하면 실질적인 instance 가 아닌 가짜 객체를 생성하는 반면에 Spy 는 실질질적으로 존재하는 instance 를 wraping 한 객체를 생성

//    @Test
//    public void user_add_test() throws Exception{
//        //BOD Mockito 방식
//        //given
//        UserParam userParam = createUserParam();
//        User user = createUserEntity(userParam);
//        //stub 동작 지정
//        /*
//            when(userRepository.save(user)).thenReturn(user);
//            기대 값과 실제 값 strict 체크, -> lenient 허술하게 체크
//         */
//        lenient().when(userRepository.save(user)).thenReturn(user);
//        //when test execute
//        Long userNo = userService.add(userParam);
//        // FIXME : db를 거치지 않기 때문에 userNo가 없음... how?
//        // 1. user 엔티티 직접 userNo를 셋팅한다. ( 테스트 때문에 setUserNo 메서드? )
//        // 2. 반환을.. userNo를 사용하지 않는다. ( 테스트 때문에? )
//        //then
//        assertEquals(user.getUserNo(), userNo);
//    }

    @Test
    public void user_getList_test() throws Exception{
        //given
        ArrayList<UserParam> userParam = createParam(3);
        ArrayList<User> userEntity = createEntity(userParam);
//        List<User> users = userRepository.saveAll(userEntity); 의미 x 어짜피 저장 안 함;
        when(userRepository.findAll()).thenReturn(userEntity);
//        //when
        List<UserDto> all = userService.findAll();
//        //then
        assertEquals(userEntity.size(), all.size());
    }

    @Test
    public void user_get_test() throws Exception{
        //given
        ArrayList<UserParam> userParam = createParam(3);
        ArrayList<User> userEntity = createEntity(userParam);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userEntity.get(0)));
        //when
        UserDto dto = userService.findById(1L);
        //then
        assertEquals(dto.getUserNo(), userEntity.get(0).getUserNo());
    }
    @Test
    public void user_update_test() throws Exception{
        //given
        /*
        Long userNo = 1L;
        User entity = createEntity(createParam());
        UserParam updateParam = updateParam();

        userRepository.updateUser();
        //FIXME : update해서 받는 entity는 실 업데이트가 안 된 엔티티인데,
        //FIXME : 검증을 할 수 없다고.. 그냥 써도 되는 게 맞을까?

        doNothing().when(userRepository).updateUser(userNo, updateParam);
        when(userRepository.findById(userNo)).thenReturn(Optional.ofNullable(entity));
        //when
        userService.update(userNo, updateParam());
        //then
        verify(userRepository, times(1)).updateUser(userNo, updateParam);
        */
    }
    @Test
    public void user_delete_test() throws Exception{
        //given

        //when

        //then
    }
}
