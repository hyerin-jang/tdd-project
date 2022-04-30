package tdd.tddproject.hyechan.service;

// 단위 테스트 ( Service 관련된 애들만 메모리에 띄움)
// BoardRepository 가짜 객체로  - Mock
//docall메서드
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import tdd.tddproject.domain.entity.user.User;
import tdd.tddproject.hyechan.dto.UserDto;
import tdd.tddproject.hyechan.repository.UserRepository;
import tdd.tddproject.hyechan.util.UserConstructor;
import tdd.tddproject.vo.user.UserParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.will;
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

    @Test
    public void user_add_test() throws Exception{
        //given
        UserParam param = createParam();
        User user = createEntity(param);
//        when(userRepository.save(user)).thenReturn(user);
//        실 UserService 에서 들어가는 엔티티 user, createEntity() !=equals
        when(userRepository.save(any())).thenReturn(user);
        //when test execute
        Long add = userService.add(param);
        //then
        assertEquals(add, user.getUserNo());
        //순서대로 return
        // when(userRepository.save(user).getUserNo()).thenReturn(1L, 2L, 3L);

    }

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
        Long userNo = 1L;
        User user = createEntity(createParam());
        UserParam updateParam = updateParam();

        user.update(updateParam.getUserName(),updateParam.getUserEmail(),updateParam.getUserPhone());
        // querydsl, repository QUERY 생성하는 거여서 사용할 수 없음. 일단 update 메서드 사용.

        doNothing().when(userRepository).updateUser(userNo, updateParam);
        when(userRepository.findById(userNo)).thenReturn(Optional.of(user));
        //when
        userService.update(userNo, updateParam());
        //then
        verify(userRepository, times(1)).updateUser(userNo, updateParam);
    }
    @Test
    public void user_delete_test() throws Exception{
        //given
        Long userNo = 1L;
        // 없어도 되는데.. 명시적으로 하기 위해 적어둔다.
        doNothing().when(userRepository).deleteById(userNo);
        //when
        userService.delete(userNo);
        //then
        verify(userRepository, times(1)).deleteById(userNo);
    }
}
