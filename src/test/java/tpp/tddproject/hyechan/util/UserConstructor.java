package tpp.tddproject.hyechan.util;

import org.mapstruct.factory.Mappers;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.hyechan.mapper.UserMapper;
import tpp.tddproject.vo.user.UserParam;

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
    public User createEntity(UserParam param) {
        return param.toEntity();
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
    public UserParam createParam() {
        UserParam userParam = new UserParam();
        userParam.setUserId("test");
        userParam.setUserName("햇찬");
        userParam.setUserPw("test123");
        userParam.setUserEmail("dhgpcks@gmail.com");
        userParam.setUserPhone("010-1111-1111");
        return userParam;
    }

    @Override
    public ArrayList<UserParam> createParam(int count) {
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
