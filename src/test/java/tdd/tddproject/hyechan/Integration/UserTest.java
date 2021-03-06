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
    ?????? ????????? : ?????? bean IoC ?????? ?????????
    + ????????? ??? ???????????????!?
    + Spring REST DOC Document ??????
 */



@ExtendWith(MockitoExtension.class)

// ?????? ????????? springboot ?????? ?????????
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//.MOCK ??? ????????? ?????? ?????? ?????? .RANDOM_PORT ??? ??????
@AutoConfigureMockMvc // mockMvc Dependency Injection.
@AutoConfigureRestDocs // RestDocs Dependency Injection
@Transactional // ???????????? ??????
class UserTest extends UserConstructor {

    @Autowired
    private MockMvc mockMvc; // url????????? ?????????

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
        //???????????? ??? ????????????
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
        // Mockito pattern ( extends BDDMockito ??????????????? ??????- ????????? up )
        // == given(???)
        String content = toJson(createParam());

        // == when(????????? ??????)
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_VALUE));

        // == then(??????)
        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("user/user_add",
                        requestFields(
                            fieldWithPath("userId").type(JsonFieldType.STRING).description("?????????"), // ??? ?????? ??????
                            fieldWithPath("userPw").type(JsonFieldType.STRING).description("????????????"),
                            fieldWithPath("userName").type(JsonFieldType.STRING).description("??????"),
                            fieldWithPath("userEmail").type(JsonFieldType.STRING).description("?????????"),
                            fieldWithPath("userPhone").type(JsonFieldType.STRING).description("??? ??????").optional() // null optional
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
                .andExpect(jsonPath("$.result.[0].userName").value( "?????????0"))
                .andExpect(jsonPath("$.result.[1].userName").value( "?????????1"))
                .andExpect(jsonPath("$.result.[0].userId").value("test0"))
                .andExpect(jsonPath("$.result.[1].userPhone").value("010-0000-0001"))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("user/user_getList"));
    }

    @Test
    @WithUser
    public void user_getList_??????????????????_??????() throws Exception{
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
                        pathParameters(parameterWithName("userNo").description("user ??????"))
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
                        pathParameters(parameterWithName("userNo").description("user ??????")),
                        requestFields(
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("?????????").optional(), // ??? ?????? ??????
                                fieldWithPath("userPw").type(JsonFieldType.STRING).description("????????????").optional(),
                                fieldWithPath("userName").type(JsonFieldType.STRING).description("??????").optional(),
                                fieldWithPath("userEmail").type(JsonFieldType.STRING).description("?????????").optional(),
                                fieldWithPath("userPhone").type(JsonFieldType.STRING).description("??? ??????").optional() // null optional
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

        // isOk ????????????????
        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("user/user_delete",
                        pathParameters(parameterWithName("userNo").description("user ??????"))
                ));
    }
}