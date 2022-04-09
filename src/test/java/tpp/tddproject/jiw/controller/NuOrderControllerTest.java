package tpp.tddproject.jiw.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import tpp.tddproject.domain.entity.nu.NuOrder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NuOrderControllerTest {

    @Autowired MockMvc mockMvc;
    NuOrder nuOrder;

    @BeforeEach
    public void setUp() {
        nuOrder = new NuOrder();
    }

    @Test
    void 테스트() throws Exception {
        mockMvc.perform(get("/"))
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
