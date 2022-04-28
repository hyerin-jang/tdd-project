package tdd.tddproject.jiw.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import tdd.tddproject.domain.entity.nu.NuRefund;
import tdd.tddproject.inwoo.controller.NuRefundController;
import tdd.tddproject.inwoo.service.NuRefundService;

@WebMvcTest(NuRefundController.class)
public class NuRefundControllerTest {

	@Autowired MockMvc mockMvc;
	@MockBean NuRefundService nuRefundService;

	@BeforeEach
	public void setUp() {

	}

	//환불 테스트 케이스
	//1. 환불
	//2. 환불 취소
	//3. 환불 조회?

	@Test
	void test() throws Exception {
		ResultActions resultActions =
			mockMvc.perform(RestDocumentationRequestBuilders.get("/"));

		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void 환불() throws Exception {
		Long nuOrderNo = 1L;

		// when(nuRefundService.환불())
		// 	.thenReturn(NuRefund);
		//비회원 주문 환불
		ResultActions resultActions =
			mockMvc.perform(RestDocumentationRequestBuilders.post("/{nuOrderNo}", nuOrderNo)
				.contentType(MediaType.APPLICATION_JSON_VALUE));

		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$..nuOrderNo").value(1))
			.andDo(print());
	}

	@Test
	void 환불_조회() throws Exception {
		//비회원 주문 환불 조회
	}

	@Test
	void 환불_취소() throws Exception {
		//비회원 주문 환불 취소
	}


}
