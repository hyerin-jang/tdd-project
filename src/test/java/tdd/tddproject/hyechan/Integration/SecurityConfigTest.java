package tdd.tddproject.hyechan.Integration;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import tdd.tddproject.domain.entity.user.Role;
import tdd.tddproject.domain.entity.user.RoleType;
import tdd.tddproject.domain.entity.user.User;
import tdd.tddproject.global.exception.ErrorCode;
import tdd.tddproject.global.exception.IdNotFoundException;
import tdd.tddproject.hyechan.repository.RoleRepository;
import tdd.tddproject.hyechan.repository.UserRepository;
import tdd.tddproject.hyechan.service.UserService;
import tdd.tddproject.hyechan.util.UserConstructor;
import tdd.tddproject.vo.user.UserParam;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Transactional
@AutoConfigureMockMvc
public class SecurityConfigTest extends UserConstructor {

//    @Autowired
//    private WebApplicationContext context;
//
    @Autowired
    private UserRepository userRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void beforeAll(){
        roleRepository.save(new Role(RoleType.ROLE_ADMIN));
        roleRepository.save(new Role(RoleType.ROLE_USER));
        roleRepository.save(new Role(RoleType.ROLE_SELLER));
    }

    // @author: hyechan, @since: 2022/05/12 3:57 오후
    @Test
    void 로그인_성공_테스트() throws Exception{
        //given
        UserParam param = createParam();
        param.setUserId("authTest");
        userService.add(param);
        //when
        mockMvc.perform(
                SecurityMockMvcRequestBuilders.formLogin().loginProcessingUrl("/login")
                        .user("authTest").password("test123"))
        //then
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/"))
                .andDo(MockMvcResultHandlers.print());
    }


}
