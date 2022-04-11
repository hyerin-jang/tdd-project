package tpp.tddproject.hyechan.controller;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.hyechan.dto.UserDto;
import tpp.tddproject.hyechan.repository.UserRepository;
import tpp.tddproject.hyechan.service.UserService;
import tpp.tddproject.vo.user.UserVO;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc // -> webAppContextSetup(webApplicationContext)
@AutoConfigureRestDocs // -> apply(documentationConfiguration(restDocumentation))
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
//@RequiredArgsConstructor
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void addUser() throws Exception{
        //given
        UserVO userVO =
                new UserVO("dhgpcks","123123","햇찬",
                        "dhgpcks@gmail","010-1111-1111");
        //when
//        userController.addUser(userVO);
        // 통합테스트로 Repository 사용해서 넣어준다.
        // Controller 따로 단위 테스트를 만들까? ( -> Mock 객체 사용 )
        // Service 따로 단위 테스트

        //then
//        List<User> list = userRepository.findAll();
//        assertThat(list.size()).isEqualTo(1);
//        assertThat(list).extracting("userId").containsExactly("dhgpcks");
    }

    /**
     * @author: hyechan
     * @since: 2022/04/11 2:06 오후
     * @description
     */
    @Test
    public void add() throws Exception{
        //given
        JSONObject result = new JSONObject();
        result.put("userId","dhgpcks123");
        result.put("userPw","123123");
        result.put("userName","햇찬");
        result.put("userEmail","dhgpcks@gmail");
        result.put("userPhone","010-1111-1111");

        //when
        when(userService.add(any())).thenReturn(1L);
        //userService에 any()으로 add를 요청하면, 특정 값이 return 된다.

        //then
        this.mockMvc.perform(
                post("/user")
                // 요청 방식은 post, /user 호출
                .content(String.valueOf(result))
                // 보내줄 값을 설정
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // 정상 작동 시 isOk( isCreated, 상태 코드로 응답을 기대
                .andDo(document("user", //요청이 완수되면 문서 생성
                        requestFields(
                                fieldWithPath("userId").description("아이디"), // 키 값과 내용
                                fieldWithPath("userPw").description("비밀번호"),
                                fieldWithPath("userName").description("이름"),
                                fieldWithPath("userEmail").description("이메일"),
                                fieldWithPath("userPhone").description("폰 번호").optional() // null optional
                        )
                ));
    }

}