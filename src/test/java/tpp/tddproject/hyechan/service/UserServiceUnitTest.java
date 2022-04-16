package tpp.tddproject.hyechan.service;

// 단위 테스트 ( Service 관련된 애들만 메모리에 띄움)
// BoardRepository 가짜 객체로  - Mock

import org.apiguardian.api.API;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.hyechan.dto.UserDto;
import tpp.tddproject.hyechan.mapper.UserMapper;
import tpp.tddproject.hyechan.repository.UserRepository;
import tpp.tddproject.vo.user.UserParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.will;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // springboot x, Mockito 따로 띄움
public class UserServiceUnitTest {

    @InjectMocks // UserService 객체 만들 때 @Mock 으로 등록된 모든 것을 주입받는다.
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    // Mock 을 사용하면 실질적인 instance 가 아닌 가짜 객체를 생성하는 반면에 Spy 는 실질질적으로 존재하는 instance 를 wraping 한 객체를 생성

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

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
//        assertEquals(user.getUserNo(),userNo);
//    }

    @Test
    public void user_getList_test() throws Exception{
        //given
        ArrayList<UserParam> userParam = createUserParam(3);
        ArrayList<User> userEntity = createUserEntity(userParam);
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
        ArrayList<UserParam> userParam = createUserParam(3);
        ArrayList<User> userEntity = createUserEntity(userParam);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userEntity.get(0)));
        //when
        UserDto dto = userService.findById(1L);
        //then
        assertEquals(dto.getUserNo(), userEntity.get(0).getUserNo());
    }
    @Test
    public void user_update_test() throws Exception{
        //given

        //when

        //then
    }
    @Test
    public void user_delete_test() throws Exception{
        //given

        //when

        //then
    }

    // == UserParam, UserEntity 생성 메서드 분리 == //
    private User createUserEntity(UserParam userParam){
        return User.builder()
                .userId(userParam.getUserId())
                .userName(userParam.getUserName())
                .userEmail(userParam.getUserEmail())
                .userPhone(userParam.getUserPhone())
                .userPw(userParam.getUserPw()).build();
    }
    private ArrayList<User> createUserEntity(ArrayList<UserParam> userParamList){
        ArrayList<User> arrayList = new ArrayList<>();
        for (UserParam userParam : userParamList) {
            User user = User.builder()
                    .userId(userParam.getUserId())
                    .userName(userParam.getUserName())
                    .userEmail(userParam.getUserEmail())
                    .userPhone(userParam.getUserPhone())
                    .userPw(userParam.getUserPw()).build();
            arrayList.add(user);
        }
        return arrayList;
    }
    private UserParam createUserParam(){
        UserParam userParam = new UserParam();
        userParam.setUserId("test");
        userParam.setUserName("햇찬");
        userParam.setUserPw("test123");
        userParam.setUserEmail("dhgpcks@gmail.com");
        userParam.setUserPhone("010-1111-1111");
        return userParam;
    }
    private ArrayList<UserParam> createUserParam(int count){
        ArrayList<UserParam> arrayList = new ArrayList<>();
        for(int i = 0; i < count; i ++){
            UserParam userParam = new UserParam();
            userParam.setUserId("test"+count);
            userParam.setUserName("테스터"+count);
            userParam.setUserPw("password"+count);
            userParam.setUserEmail(count+"@gmail.com");
            userParam.setUserPhone("010-0000-000"+count);
            arrayList.add(userParam);
        }
        return arrayList;
    }

}
