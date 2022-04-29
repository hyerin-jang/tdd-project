package tdd.tddproject.hyechan.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;
import tdd.tddproject.domain.entity.user.User;
import tdd.tddproject.hyechan.mapper.UserMapper;
import tdd.tddproject.vo.user.UserParam;

import java.util.ArrayList;

/**
 * fileName    : UserTestUtil
 * author      : hyechan
 * date        : 2022/04/17
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/04/17 6:02 오전  hyechan        최초 생성
 */
public class UserConstructor implements ConstructorCreate<User, UserParam> {

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Override
    public User createEntity(UserParam param){
        User user = param.toEntity();
        ReflectionTestUtils.setField(user, "userNo", 1L);
        return user;
    }

    @Override
    public ArrayList<User> createEntity(ArrayList<UserParam> paramList) {
        ArrayList<User> arrayList = new ArrayList<>();
        for (UserParam param : paramList) {
            User user = param.toEntity();
            arrayList.add(user);
        }
        return arrayList;
    }

    @Override
    public ArrayList<User> createEntity(ArrayList<UserParam> paramList, String id) {
        ArrayList<User> arrayList = new ArrayList<>();
        Long index = 0L;
        for (UserParam param : paramList) {
            User user = param.toEntity();
            ReflectionTestUtils.setField(user, id, index);
            arrayList.add(user);
            index++;
        }
        return arrayList;
    }

    @Override
    public UserParam createParam() {
        UserParam param = new UserParam();
        param.setUserId("test");
        param.setUserName("햇찬");
        param.setUserPw("test123");
        param.setUserEmail("dhgpcks@gmail.com");
        param.setUserPhone("010-1111-1111");
        return param;
    }

    @Override
    public ArrayList<UserParam> createParam(int count) {
        ArrayList<UserParam> arrayList = new ArrayList<>();
        for(int i = 0; i < count; i ++){
            UserParam param = new UserParam();
            param.setUserId("test"+i);
            param.setUserName("테스터"+i);
            param.setUserPw("password"+i);
            param.setUserEmail(i+"@gmail.com");
            param.setUserPhone("010-0000-000"+i);
            arrayList.add(param);
        }
        return arrayList;
    }
    @Override
    public UserParam updateParam() {
        UserParam userParam = new UserParam();
        userParam.setUserName("update");
        userParam.setUserEmail("update@gmail.com");
        return userParam;
    }
    @Override
    public String toJson(UserParam param) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(param);
    }


}
