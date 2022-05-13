package tdd.tddproject.hyechan.Integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.user.Role;
import tdd.tddproject.domain.entity.user.RoleType;
import tdd.tddproject.hyechan.repository.RoleRepository;
import tdd.tddproject.hyechan.service.UserService;
import tdd.tddproject.hyechan.util.UserConstructor;
import tdd.tddproject.vo.user.UserParam;



import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Transactional
@AutoConfigureMockMvc
public class SecurityConfigTest extends UserConstructor {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    // FIXME : 카테고리 넣어주는 건... 항상 필요
    // 한번만 할 수 있는 방법은 없을까?
    @BeforeEach
    public void beforeEach(){
        //카테고리 값 넣어주기
        roleRepository.save(new Role(RoleType.ROLE_ADMIN));
        roleRepository.save(new Role(RoleType.ROLE_USER));
        roleRepository.save(new Role(RoleType.ROLE_SELLER));
        // 유저등록
        UserParam param = createParam();
        param.setUserId("authTest");
        userService.add(param);
    }

    // @author: hyechan, @since: 2022/05/12 3:57 오후
    @Test
    void 로그인_성공_테스트() throws Exception{
        //given
        //when
        mockMvc.perform(
                SecurityMockMvcRequestBuilders.formLogin().loginProcessingUrl("/login")
                        .user("authTest").password("test123"))
        //then
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/"))
                .andDo(MockMvcResultHandlers.print());
    }

    // @author: hyechan, @since: 2022/05/13 4:31 오후
    @Test
    void 로그인_실패_테스트() throws Exception{
        //given
        //when
        mockMvc.perform(
                SecurityMockMvcRequestBuilders.formLogin().loginProcessingUrl("/login")
                    .user("abc123").password("test123"))
        //then
                .andExpect(unauthenticated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    // FIXME : 추가적으로 확인하고 싶은데 어떻게 해야할까? ex) session 확인
    void 로그아웃_성공_테스트() throws Exception{
        //given
        mockMvc.perform(
                SecurityMockMvcRequestBuilders.formLogin().loginProcessingUrl("/login")
                        .user("authTest").password("test123"))
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/"));
        //when
        mockMvc.perform(
                SecurityMockMvcRequestBuilders.logout("/logout"))
                //then
                .andExpect(status().is3xxRedirection()) // redirect 발생
                .andExpect(redirectedUrl("/"))
                .andExpect(unauthenticated())
                .andDo(MockMvcResultHandlers.print());
    }



}
