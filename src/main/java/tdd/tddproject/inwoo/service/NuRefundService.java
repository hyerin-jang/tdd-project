package tdd.tddproject.inwoo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tdd.tddproject.domain.entity.nu.NuRefund;
import tdd.tddproject.inwoo.repository.NuRefundRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NuRefundService {

	private final NuRefundRepository nuRefundRepository;

	public void refund(Long nuRefundNo) {
		NuRefund nuRefund = nuRefundRepository.findById(nuRefundNo)
				.orElseThrow(NoSuchElementException::new);

		nuRefund.setRefund();

		nuRefundRepository.save(nuRefund);
	}

	public NuRefund getNuRefund(Long nuRefundNo) {
		//TODO JPA에서 enum 어떻게 다루는지? or 해당 Y, N값을 어떻게 유틸 클래스로 빼서 사용할지 알아보기
		return nuRefundRepository.findByNuRefundNoAndNuRefundYn(nuRefundNo, "Y")
				.orElseThrow(NoSuchElementException::new);
	}

	public void cancelRefund(Long nuRefundNo) {
		NuRefund nuRefund = nuRefundRepository.findById(nuRefundNo)
				.orElseThrow(NoSuchElementException::new);

		nuRefund.setRefundCancel();

		nuRefundRepository.save(nuRefund);
	}
}
