package tpp.tddproject.hyechan.controller.unitTest;


// 단위 테스트 : Controller, Filter, ControllerAdvice

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.hyechan.dto.UserDto;
import tpp.tddproject.hyechan.mapper.UserMapper;
import tpp.tddproject.hyechan.service.UserService;
import tpp.tddproject.rin.mapper.ItemMapper;
import tpp.tddproject.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest // include MockMvc
//단위테스트 WebMvcTest 테스트 하는 것만 메모리에 뜬다 = 통합 보다 가볍다
//@ExtendWith(SpringExtension.class) // Spring 환경 테스트로 확장, JUNIT4: RunWith(SpringRunner.class)
@Slf4j
public class UserControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // IoC 환경 가짜 bean 등록
    private UserService userService;

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    //controller 에서 사용하기 위해 가짜객체를 만들건데..
    //UserController 를 @MockInjects 받아줘야 한다.
    //@WebMvcTest, Spring 환경 테스트 확장 되어 중복 => MockBean

    @Test
    public void user_add_test() throws Exception{
        // BDD Mockito pattern (extends BDDMockito 라이브러리 사용가능)
        // == given(값)
        UserVO user1 = new UserVO(
                "test", "test123", "햇찬", "dhgpcks@gmail.com", "010-1111-1111"
        );
        String content = new ObjectMapper().writeValueAsString(user1); //Object->Json

        //userService 가짜 객체이므로, 무엇을 반환할지 정해줘야 한다.
        when(userService.add(user1)).thenReturn(0L);
        // == when(테스트 진행)
        ResultActions resultActions = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_VALUE));

        // == then(검증)
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("0"))
                //jsonPath : $.(모두) json 형식 데이터 찾음, 필요시 jsonPath 문법
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void user_getList_test() throws Exception{
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

        List<UserDto> userDtoList = mapper.toDtoList(new ArrayList<>(Arrays.asList(user1, user2)));

        when(userService.findAll()).thenReturn(userDtoList);
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
