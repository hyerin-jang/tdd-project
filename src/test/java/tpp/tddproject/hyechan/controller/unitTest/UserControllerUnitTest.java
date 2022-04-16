package tpp.tddproject.hyechan.controller.unitTest;


// 단위 테스트 : Controller, Filter, ControllerAdvice

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.hyechan.dto.UserDto;
import tpp.tddproject.hyechan.mapper.UserMapper;
import tpp.tddproject.hyechan.service.UserService;
import tpp.tddproject.vo.user.UserParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
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
        // BOD Mockito pattern (extends BODMockito 라이브러리 사용가능)
        // == given(값)
        UserParam user1 = new UserParam(
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

    @Test
    public void user_get_test() throws Exception{
        // given
        Long userNo = 1L;
        User user = User.builder()
                .userId("test")
                .userName("one")
                .userEmail("dhgpcks@gmail.com")
                .userPhone("010-1111-1111").build();

        when(userService.findById(userNo)).thenReturn(mapper.toDto(user));
        // when

        ResultActions resultActions = mockMvc.perform(get("/user/{userNo}", userNo)
                .accept(MediaType.APPLICATION_JSON_VALUE));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.userId").value("test"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void user_update_test() throws Exception{
        //given
        // 전 데이터
        Long userNo = 1L;
        UserParam userParam = new UserParam("dhgpcks", "test123", "햇찬", "dhgpcks@gmail.com", "010-1111-1111");
        // 업데이트 데이터
        UserParam updateUserParam = new UserParam();
        updateUserParam.setUserName("수정된 이름");
        updateUserParam.setUserPhone("010-3333-3333");
        String content = new ObjectMapper().writeValueAsString(updateUserParam); //Object->Json

        User user = User.builder()
                .userId(userParam.getUserId())
                .userName(updateUserParam.getUserName())
                .userEmail(userParam.getUserEmail())
                .userPhone(updateUserParam.getUserPhone()).build();

        when(userService.update(userNo, updateUserParam)).thenReturn(mapper.toDto(user));
        //when
        ResultActions resultActions = mockMvc.perform(put("/user/{userNo}", userNo)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.userId").value("dhgpcks"))
                .andExpect(jsonPath("$.result.userName").value("수정된 이름"))
                .andExpect(jsonPath("$.result.userEmail").value("dhgpcks@gmail.com"))
                .andExpect(jsonPath("$.result.userPhone").value("010-3333-3333"))
                .andDo(MockMvcResultHandlers.print());
        // 의미가 있나?
    }
        /*
        void 반환하는 경우?
        : mock userService 로 유닛 테스트를 만들고 싶다.
        우리는 mock void method 사용 할 수 없다.
        왜냐하면 모키토의 when()은 void 에서 작동하지 않기 때문이다.
        해결 방법 4가지가 있다.
        1) doNothing : Completely ignore the calling of void method, this is default behavior
        2) doAnswer : Perform some run time or complex operations when void method is called
            -> 진짜 메서드를 호출하고 싶진 않지만, 런타임 잠깐 동안 동작이 필요하면 doAnswer 사용
        3) doThrow : Throw exception when mocked void method is called
            -> 메서드 호출했을 때 예외 발생시키고 싶을 때 쓴다.
        4) doCallRealMethod : Do not mock and call real method
            -> 그냥 진짜 메서드 호출
     */
    /*
    1)  void 부르는 걸 완전히 무시하고 싶으면, doNoting()을 써라.
        mocking 에서 mocked 오브젝트의 모든 메서드는 doNothing 이 디폴트 값이다,
     */
    @Test
    public void user_delete_test_doNothing() throws Exception{
        //given
        Long userNo = 1L;
        doNothing().when(userService).delete(anyLong()); //default, 주석처리해도 동작
        //when
        ResultActions resultActions = mockMvc.perform(delete("/user/{userNo}", userNo)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        //then
        // 한번 불렀는가?
        verify(userService, times(1)).delete(userNo);
        // isOk 반환했는가?
        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
