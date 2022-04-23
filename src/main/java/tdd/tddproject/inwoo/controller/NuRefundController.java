package tdd.tddproject.inwoo.controller;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import tdd.tddproject.domain.entity.nu.NuRefund;
import tdd.tddproject.inwoo.service.NuRefundService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/refund")
public class NuRefundController {

	private final NuRefundService nuRefundService;

	//환불
	@PutMapping("/{nuRefundNo}")
	public void refund(@PathVariable Long nuRefundNo) {
		nuRefundService.refund(nuRefundNo);
	}

	//환불 조회
	@GetMapping("/{nuRefundNo}")
	public NuRefund getNuRefund(@PathVariable Long nuRefundNo) {
		return nuRefundService.getNuRefund(nuRefundNo);
	}

	//환불 취소
	@PutMapping("/cancel/{nuRefundNo}")
	public void cancelRefund(@PathVariable Long nuRefundNo) {
		nuRefundService.cancelRefund(nuRefundNo);
	}
}
