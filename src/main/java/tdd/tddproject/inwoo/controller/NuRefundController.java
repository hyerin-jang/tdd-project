package tdd.tddproject.inwoo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tdd.tddproject.inwoo.service.NuRefundService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/refund")
public class NuRefundController {

	private final NuRefundService nuRefundService;

	//환불
	@PostMapping("/{nuOrderNo}")
	public void 환불(@PathVariable Long nuOrderNo) { //TODO 메서드명 변경하기
		nuRefundService.환불();
	}


	//환불 취소

	//환불 조회

}
