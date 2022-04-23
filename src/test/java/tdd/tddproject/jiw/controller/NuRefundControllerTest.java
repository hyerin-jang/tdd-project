package tdd.tddproject.jiw.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NuRefundController.class)
public class NuRefundControllerTest {

	@Autowired MockMvc mockMvc;
	@MockBean NuRefundService nuRefundService;

	NuRefund nuRefund;

	@BeforeEach
	public void setUp() {
		nuRefund = NuRefund
				.builder()
				.nuRefundNo(1L)
				.build();
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
		Long nuRefundNo = 1L;

		doNothing().when(nuRefundService).refund(nuRefundNo);
		//비회원 주문 환불
		ResultActions resultActions =
			mockMvc.perform(RestDocumentationRequestBuilders.put("/refund/{nuRefundNo}", nuRefundNo)
				.contentType(MediaType.APPLICATION_JSON_VALUE));

		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void 환불_조회() throws Exception {
		//비회원 주문 환불 조회
		Long nuRefundNo = 1L;

		when(nuRefundService.getNuRefund(nuRefundNo))
				.thenReturn(nuRefund);

		ResultActions resultActions =
				mockMvc.perform(RestDocumentationRequestBuilders.get("/refund/{nuRefundNo}", nuRefundNo)
					.contentType(MediaType.APPLICATION_JSON_VALUE));

		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$..nuRefundNo").value(1))
				.andDo(print());
	}

	@Test
	void 환불_취소() throws Exception {
		//비회원 주문 환불 취소
		Long nuRefundNo = 1L;

		doNothing().when(nuRefundService).cancelRefund(nuRefundNo);

		ResultActions resultActions =
			mockMvc.perform(RestDocumentationRequestBuilders.put("/refund/cancel/{nuRefundNo}", nuRefundNo)
					.contentType(MediaType.APPLICATION_JSON_VALUE));

		resultActions
				.andExpect(status().isOk())
				.andDo(print());
	}
}
