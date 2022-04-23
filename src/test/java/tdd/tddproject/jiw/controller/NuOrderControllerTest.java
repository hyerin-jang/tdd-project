package tdd.tddproject.jiw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tdd.tddproject.domain.entity.nu.NuOrder;
import tdd.tddproject.inwoo.controller.NuOrderController;
import tdd.tddproject.inwoo.service.NuOrderService;
import tdd.tddproject.inwoo.service.ResponseService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NuOrderController.class)
public class NuOrderControllerTest {

    @Autowired MockMvc mockMvc;
//    @MockBeans({@MockBean(NuOrderService.class), @MockBean(ResponseService.class)})
//    @MockBeans(value = {@MockBean(NuOrderService.class), @MockBean(ResponseService.class)})
    //Mock 객체 의존성 주입
    @MockBean NuOrderService nuOrderService;
    @MockBean ResponseService responseService;

    NuOrder nuOrder;

    @BeforeEach
    public void setUp() {
        nuOrder = NuOrder.builder()
                .build();

    }

    @Test
    void 테스트() throws Exception {
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/"));

        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 비회원_조회() throws Exception {
        Long nuOrderNo = 1L;
        NuOrder nuOrder = NuOrder.builder()
                .nuOrderNo(nuOrderNo)
                .build();
        when(nuOrderService.getNuOrder(nuOrderNo))
                .thenReturn(java.util.Optional.ofNullable(nuOrder));

        ResultActions resultActions = mockMvc.perform(get("/{nuOrderNo}", nuOrderNo)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        resultActions
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.result.nuOrderNo").value(nuOrderNo))
                .andDo(print());
    }

    @Test
    void 비회원_리스트_조회() throws Exception {
        List<NuOrder> nuOrderList = new ArrayList<>() {{
            add(NuOrder.builder()
                    .nuOrderNo(1L)
                    .build());
            add(NuOrder.builder()
                    .nuOrderNo(2L)
                    .build());
        }};
        when(nuOrderService.getNuOrderList())
                .thenReturn(nuOrderList);

        ResultActions resultActions = mockMvc.perform(get(("/"))
                .contentType(MediaType.APPLICATION_JSON_VALUE));
//                .accept(MediaType.APPLICATION_JSON_VALUE));

        resultActions
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.result", Matchers.hasSize(2)))
//                .andExpect(jsonPath("$.result[0].nuOrderNo").value("1"))
//                .andExpect(jsonPath("$.result[1].nuOrderNo").value("2"))
                .andDo(print());

    }

    @Test
    void 비회원_주문() throws Exception {
        NuOrder nuOrder = NuOrder.builder()
                .nuOrderNo(1L)
                .build();

        String nuOrderContent = new ObjectMapper().writeValueAsString(nuOrder);
        doNothing().when(nuOrderService).saveNuOrder(nuOrder);
        ResultActions resultActions = mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(nuOrderContent));

        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 비회원_수정() throws Exception {
        Long nuOrderNo = 1L;
        NuOrder nuOrder = NuOrder.builder()
                .nuOrderNo(nuOrderNo)
                .build();

        String nuOrderContent = new ObjectMapper().writeValueAsString(nuOrder);
        doNothing().when(nuOrderService).saveNuOrder(nuOrder);
        ResultActions resultActions = mockMvc.perform(put("/{nuOrderNo}", nuOrderNo)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(nuOrderContent));

        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 비회원_삭제() throws Exception {
        Long nuOrderNo = 1L;

        ResultActions resultActions = mockMvc.perform(delete("/{nuOrderNo}", nuOrderNo)
                .accept(MediaType.APPLICATION_JSON_VALUE));
//                .contentType(MediaType.APPLICATION_JSON_VALUE));

        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

}
