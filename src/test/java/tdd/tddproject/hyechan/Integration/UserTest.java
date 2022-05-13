package tdd.tddproject.hyechan.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.user.Role;
import tdd.tddproject.domain.entity.user.RoleType;
import tdd.tddproject.domain.entity.user.User;
import tdd.tddproject.global.WithAdmin;
import tdd.tddproject.global.WithUser;
import tdd.tddproject.global.exception.ErrorCode;
import tdd.tddproject.global.exception.IdNotFoundException;
import tdd.tddproject.hyechan.mapper.UserMapper;
import tdd.tddproject.hyechan.repository.RoleRepository;
import tdd.tddproject.hyechan.repository.UserRepository;
import tdd.tddproject.hyechan.service.UserService;
import tdd.tddproject.hyechan.util.UserConstructor;
import tdd.tddproject.vo.user.UserParam;

import javax.persistence.EntityManager;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
    통합 테스트 : 모든 bean IoC 올려 테스트
    + 전체가 잘 돌아가는가!?
    + Spring REST DOC Document 생성
 */



@ExtendWith(MockitoExtension.class)

// 통합 테스트 springboot 전반 테스트
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//.MOCK 실 톰켓이 아닌 모의 톰켓 .RANDOM_PORT 실 톰캣
@AutoConfigureMockMvc // mockMvc Dependency Injection.
@AutoConfigureRestDocs // RestDocs Dependency Injection
@Transactional // 트랜잭션 롤백
class UserTest extends UserConstructor {

    @Autowired
    private MockMvc mockMvc; // url주소로 테스트

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    private EntityManager entityManager;
    private JPAQueryFactory jpaQueryFactory;

    Role roleUser;

    @BeforeEach
    public void init() {
        entityManager.createNativeQuery("ALTER SEQUENCE HIBERNATE_SEQUENCE RESTART WITH 1 ").executeUpdate();
        //카테고리 값 넣어주기
        roleRepository.save(new Role(RoleType.ROLE_ADMIN));
        roleRepository.save(new Role(RoleType.ROLE_USER));
        roleRepository.save(new Role(RoleType.ROLE_SELLER));
        roleUser = roleRepository.findByRoleName(RoleType.ROLE_USER).orElseThrow(() -> new IdNotFoundException(ErrorCode.ROLE_NOT_EXIST));
    }

    @AfterEach
    public void end() {
        userRepository.deleteAll();
    }

    @Test
    public void user_add_iTest() throws Exception {
        // Mockito pattern ( extends BDDMockito 라이브러리 활용- 가독성 up )
        // == given(값)
        String content = toJson(createParam());

        // == when(테스트 진행)
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_VALUE));

        // == then(검증)
        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("user/user_add",
                        requestFields(
                            fieldWithPath("userId").type(JsonFieldType.STRING).description("아이디"), // 키 값과 내용
                            fieldWithPath("userPw").type(JsonFieldType.STRING).description("비밀번호"),
                            fieldWithPath("userName").type(JsonFieldType.STRING).description("이름"),
                            fieldWithPath("userEmail").type(JsonFieldType.STRING).description("이메일"),
                            fieldWithPath("userPhone").type(JsonFieldType.STRING).description("폰 번호").optional() // null optional
                        )
                ));
    }

    @Test
    @WithAdmin
    public void user_getList_iTest() throws Exception{
        //given
        ArrayList<User> entityList = createEntity(createParam(2));
        for (User entity : entityList) {
            entity.setRole(roleUser);
        }
        userRepository.saveAll(entityList);
        ResultActions resultActions = mockMvc.perform(get(("/user"))
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.result.[0].userName").value( "테스터0"))
                .andExpect(jsonPath("$.result.[1].userName").value( "테스터1"))
                .andExpect(jsonPath("$.result.[0].userId").value("test0"))
                .andExpect(jsonPath("$.result.[1].userPhone").value("010-0000-0001"))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("user/user_getList"));
    }

    @Test
    @WithUser
    public void user_getList_권한으로인한_실패() throws Exception{
        //given
        ArrayList<User> entityList = createEntity(createParam(2));
        for (User entity : entityList) {
            entity.setRole(roleUser);
        }
        userRepository.saveAll(entityList);
        ResultActions resultActions = mockMvc.perform(get(("/user"))
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        resultActions
                .andExpect(status().isForbidden());
    }


    @Test
    @WithUser
    public void user_get_iTest() throws Exception{
        // given
        Long userNo = 1L;
        ArrayList<User> entityList = createEntity(createParam(2));
        for (User entity : entityList) {
            entity.setRole(roleUser);
        }
        userRepository.saveAll(entityList);
        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/user/{userNo}", userNo)
                .accept(MediaType.APPLICATION_JSON_VALUE));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.userId").value("test0"))
                .andExpect(jsonPath("$.result.userEmail").value("0@gmail.com"))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("user/user_get",
                        pathParameters(parameterWithName("userNo").description("user 번호"))
                ));
    }

    @Test
    @WithUser
    public void user_update_iTest() throws Exception{
        //given
        Long userNo = 1L;
        User entity = createEntity(createParam());
        entity.setRole(roleUser);
        userRepository.save(entity);

        UserParam updateParam = new UserParam();
        updateParam.setUserName("dhgpcks");
        updateParam.setUserPhone("010-3333-3333");
        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.put("/user/{userNo}", userNo)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(updateParam))
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.userId").value("test"))
                .andExpect(jsonPath("$.result.userName").value("dhgpcks"))
                .andExpect(jsonPath("$.result.userEmail").value("dhgpcks@gmail.com"))
                .andExpect(jsonPath("$.result.userPhone").value("010-3333-3333"))
                .andDo(document("user/user_update",
                        pathParameters(parameterWithName("userNo").description("user 번호")),
                        requestFields(
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("아이디").optional(), // 키 값과 내용
                                fieldWithPath("userPw").type(JsonFieldType.STRING).description("비밀번호").optional(),
                                fieldWithPath("userName").type(JsonFieldType.STRING).description("이름").optional(),
                                fieldWithPath("userEmail").type(JsonFieldType.STRING).description("이메일").optional(),
                                fieldWithPath("userPhone").type(JsonFieldType.STRING).description("폰 번호").optional() // null optional
                        )
                ));
    }

    @Test
    @WithUser
    public void user_delete_iTest_doNothing() throws Exception{
        //given
        Long userNo = 1L;
        ArrayList<User> entityList = createEntity(createParam(2));
        for (User entity : entityList) {
            entity.setRole(roleUser);
        }
        userRepository.saveAll(entityList);
        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.delete("/user/{userNo}", userNo)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .with(csrf())
        );
        //then
        assertEquals(userRepository.findAll().size(), 1);

        // isOk 반환했는가?
        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("user/user_delete",
                        pathParameters(parameterWithName("userNo").description("user 번호"))
                ));
    }
}