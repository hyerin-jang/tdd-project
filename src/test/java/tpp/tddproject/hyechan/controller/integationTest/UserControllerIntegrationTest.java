package tpp.tddproject.hyechan.controller.integationTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.hyechan.dto.UserDto;
import tpp.tddproject.hyechan.mapper.UserMapper;
import tpp.tddproject.hyechan.repository.UserRepository;
import tpp.tddproject.hyechan.service.UserService;
import tpp.tddproject.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // url주소로 테스트

    @Autowired
    private UserRepository userRepository;

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void user_add_iTest() throws Exception {
        // Mockito pattern ( extends BDDMockito 라이브러리 활용- 가독성 up )
        // == given(값)
        UserVO user1 = new UserVO(
                "test", "test123", "햇찬", "dhgpcks@gmail.com", "010-1111-1111"
        );
        String content1 = new ObjectMapper().writeValueAsString(user1); //Object->Json

        // == when(테스트 진행)
        ResultActions resultActions = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content1)
                .accept(MediaType.APPLICATION_JSON_VALUE));

        // == then(검증)
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("0"))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("user",
                        requestFields(
                            fieldWithPath("userId").description("아이디"), // 키 값과 내용
                            fieldWithPath("userPw").description("비밀번호"),
                            fieldWithPath("userName").description("이름"),
                            fieldWithPath("userEmail").description("이메일"),
                            fieldWithPath("userPhone").description("폰 번호").optional() // null optional
                        )
                ));
    }

    @Test
    public void user_getList_iTest() throws Exception{
        // mapStruct, modelMapper 를 쓸 경우 list 값은 어떻게 담는가? -> entityBuilder사용
        //given

        User user1 = User.builder()
                .userId("test1")
                .userName("one")
                .userEmail("dhgpcks@gmail.com")
                .userPhone("010-1111-1111").build();

        User user2 = User.builder()
                .userId("test2")
                .userName("two")
                .userEmail("dhgpcks@naver.com")
                .userPhone("010-2222-2222").build();

        userRepository.save(user1);
        userRepository.save(user2);
        //when
        ResultActions resultActions = mockMvc.perform(get(("/user"))
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.result.[0].userName").value( "one"))
                .andExpect(jsonPath("$.result.[1].userName").value( "two"))
                .andExpect(jsonPath("$.result.[0].userId").value("test1"))
                .andExpect(jsonPath("$.result.[1].userPhone").value("010-2222-2222"))
                .andDo(MockMvcResultHandlers.print());
    }

}