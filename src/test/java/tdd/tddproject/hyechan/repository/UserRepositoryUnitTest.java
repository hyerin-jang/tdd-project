package tdd.tddproject.hyechan.repository;


// 단위 테스트 ( DB 관련 bean IoC에 등록되면 됨)

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.user.Role;
import tdd.tddproject.domain.entity.user.RoleType;
import tdd.tddproject.domain.entity.user.User;
import tdd.tddproject.global.exception.ErrorCode;
import tdd.tddproject.global.exception.IdNotFoundException;
import tdd.tddproject.hyechan.util.UserConstructor;
import tdd.tddproject.vo.user.UserParam;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@Transactional DataJpaTest에 들어있음.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // 가짜 DB, Replace.NONE 실제 DB
@DataJpaTest // jpa 관련 빈, SpringExtension 포함
public class UserRepositoryUnitTest extends UserConstructor {

    @Autowired
    private UserRepository userRepository;

    @Autowired RoleRepository roleRepository;

    @Autowired
    private EntityManager entityManager;

    Role roleUser;

    @BeforeEach
    public void init(){
        entityManager.createNativeQuery("ALTER SEQUENCE HIBERNATE_SEQUENCE RESTART WITH 1 ").executeUpdate();
        roleRepository.save(new Role(RoleType.ROLE_ADMIN));
        roleRepository.save(new Role(RoleType.ROLE_USER));
        roleRepository.save(new Role(RoleType.ROLE_SELLER));
        roleUser = roleRepository.findByRoleName(RoleType.ROLE_USER).orElseThrow(() -> new IdNotFoundException(ErrorCode.ROLE_NOT_EXIST));
    }

    @Test
    public void user_add_test() throws Exception{
        //given
        UserParam userParam = createParam();
        User userEntity = createEntity(userParam);
        //when
        User save = userRepository.save(userEntity);
        //then
        assertEquals("햇찬", save.getUserName());
    }
    @Test
    public void user_getList_test() throws Exception{
        //given
        ArrayList<UserParam> userParam = createParam(3);
        ArrayList<User> userEntity = createEntity(userParam);
        for (User user : userEntity) {
            user.setRole(roleUser);
        }
        List<User> users = userRepository.saveAll(userEntity);
        //when
        List<User> all = userRepository.findAll();
        //then
        assertEquals(3, all.size());
    }

    @Test
    public void user_get_test() throws Exception{
        //given
        UserParam userParam = createParam();
        User userEntity = createEntity(userParam);
        User user = userRepository.save(userEntity);
        //when
        boolean present = userRepository.findById(user.getUserNo()).isPresent();
        //then
        assertTrue(present);
    }

    @Test
    public void user_update_test() throws Exception{
        //given
        UserParam userParam = createParam();
        User userEntity = createEntity(createParam());
        userEntity.setRole(roleUser);
        User user = userRepository.save(userEntity);

        //when
        userRepository.updateUser(user.getUserNo(),new UserParam(null,null,"수정이름",null,null));
        //then
        User entity = userRepository.findById(user.getUserNo())
                .orElseThrow(()->new IdNotFoundException(ErrorCode.USER_NOT_EXIST));
        assertEquals(entity.getUserName(),"수정이름");
        assertEquals(entity.getUserEmail(),"dhgpcks@gmail.com");
    }

    @Test
    public void user_delete_test() throws Exception{
        //given
        ArrayList<UserParam> userParam = createParam(3);
        ArrayList<User> userEntity = createEntity(userParam);
        for (User user : userEntity) {
            user.setRole(roleUser);
        }
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
}
