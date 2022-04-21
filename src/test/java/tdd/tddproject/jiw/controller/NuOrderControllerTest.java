package tdd.tddproject.jiw.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tdd.tddproject.domain.entity.nu.NuOrder;
import tdd.tddproject.inwoo.controller.NuOrderController;
import tdd.tddproject.inwoo.service.NuOrderService;
import tdd.tddproject.inwoo.service.ResponseService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NuOrderController.class)
public class NuOrderControllerTest {

    @Autowired MockMvc mockMvc;
    //TODO https://blusky10.tistory.com/330
//    @MockBeans({@MockBean(NuOrderService.class), @MockBean(ResponseService.class)})
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
    void 비회원_주문() throws Exception {
        mockMvc.perform(post("/order"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
