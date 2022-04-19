package tdd.tddproject.jiw.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tdd.tddproject.domain.entity.nu.NuOrder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NuOrderControllerTest {

    @Autowired MockMvc mockMvc;
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
