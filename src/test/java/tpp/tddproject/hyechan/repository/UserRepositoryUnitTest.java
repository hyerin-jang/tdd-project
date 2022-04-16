package tpp.tddproject.hyechan.repository;


// 단위 테스트 ( DB 관련 bean IoC에 등록되면 됨)

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.vo.user.UserParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // 가짜 DB, Replace.NONE 실제 DB
@DataJpaTest // jpa 관련 빈, SpringExtension 포함
public class UserRepositoryUnitTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void user_add_test() throws Exception{
        //given
        UserParam userParam = createUserParam();
        User userEntity = createUserEntity(userParam);
        //when
        User save = userRepository.save(userEntity);
        //then
        assertEquals("햇찬", save.getUserName());
    }
    @Test
    public void user_getList_test() throws Exception{
        //given
        ArrayList<UserParam> userParam = createUserParam(3);
        ArrayList<User> userEntity = createUserEntity(userParam);
        List<User> users = userRepository.saveAll(userEntity);
        //when
        List<User> all = userRepository.findAll();
        //then
        assertEquals(3, all.size());
    }

    @Test
    public void user_get_test() throws Exception{
        //given
        UserParam userParam = createUserParam();
        User userEntity = createUserEntity(userParam);
        User user = userRepository.save(userEntity);
        //when
        boolean present = userRepository.findById(user.getUserNo()).isPresent();
        //then
        assertTrue(present);
    }

    @Test
    public void user_update_test() throws Exception{
        //given
        UserParam userParam = createUserParam();
        User userEntity = createUserEntity(userParam);
        User user = userRepository.save(userEntity);
        //when
        userRepository.updateUser(user.getUserNo(),new UserParam(null,null,"수정이름",null,null));
        //then
        Optional<User> byId = userRepository.findById(user.getUserNo());
        assertEquals(byId.orElseThrow().getUserName(),"수정이름");
        assertEquals(byId.orElseThrow().getUserEmail(),"dhgpcks@gmail.com");
    }

    @Test
    public void user_delete_test() throws Exception{
        //given
        ArrayList<UserParam> userParam = createUserParam(3);
        ArrayList<User> userEntity = createUserEntity(userParam);
        List<User> users = userRepository.saveAll(userEntity);
        User user = users.get(0);
        //when
        userRepository.delete(user);
        //then
        assertEquals(userRepository.findAll().size(), 2);
        assertThrows(NoSuchElementException.class,()->{
            userRepository.findById(user.getUserNo()).orElseThrow();
        });
    }


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
