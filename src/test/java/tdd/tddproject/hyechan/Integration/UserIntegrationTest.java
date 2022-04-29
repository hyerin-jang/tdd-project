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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.user.User;
import tdd.tddproject.hyechan.mapper.UserMapper;
import tdd.tddproject.hyechan.repository.UserRepository;
import tdd.tddproject.hyechan.service.UserService;
import tdd.tddproject.hyechan.util.UserConstructor;
import tdd.tddproject.vo.user.UserParam;

import javax.persistence.EntityManager;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
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
class UserIntegrationTest extends UserConstructor {

    @Autowired
    private MockMvc mockMvc; // url주소로 테스트

    @Autowired
    private UserRepository userRepository;


    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    private EntityManager entityManager;
    private JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    public void init() {
        entityManager.createNativeQuery("ALTER SEQUENCE HIBERNATE_SEQUENCE RESTART WITH 1 ").executeUpdate();
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
                .accept(MediaType.APPLICATION_JSON_VALUE));

        // == then(검증)
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("1"))
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
    public void user_getList_iTest() throws Exception{
        //given
        userRepository.saveAll(createEntity(createParam(2)));

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
    public void user_get_iTest() throws Exception{
        // given
        Long userNo = 1L;
        userRepository.saveAll(createEntity(createParam(2)));
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
    public void user_update_iTest() throws Exception{
        //given
        Long userNo = 1L;
        userRepository.saveAll(createEntity(createParam(2)));

        UserParam updateParam = new UserParam();
        updateParam.setUserName("dhgpcks");
        updateParam.setUserPhone("010-3333-3333");
        String content = toJson(updateParam);
        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.put("/user/{userNo}", userNo)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.userId").value("test0"))
                .andExpect(jsonPath("$.result.userName").value("dhgpcks"))
                .andExpect(jsonPath("$.result.userEmail").value("0@gmail.com"))
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
    public void user_delete_iTest_doNothing() throws Exception{
        //given
        Long userNo = 1L;
        userRepository.saveAll(createEntity(createParam(2)));
        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.delete("/user/{userNo}", userNo)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
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