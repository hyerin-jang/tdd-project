package tdd.tddproject.jiw.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.tddproject.domain.entity.nu.NuRefund;
import tdd.tddproject.inwoo.service.NuRefundService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NuRefundServiceTest {
	//@MockBean, @InjectMock 차이 찾아보기
    //MvcMock, @Mock 어노테이션 차이 찾아보기
	@Mock
    NuRefundService nuRefundService;

    //환불 테스트 케이스
    //1. 환불
    //2. 환불 취소
    //3. 환불 조회?

    NuRefund nuRefund;
    NuRefund cancelNuRefund;

    @BeforeEach
    void setUp() {
        nuRefund = NuRefund
                .builder()
                .nuRefundNo(1L)
                .nuRefundYn("Y")
                .build();
    }

    @Test
    void 환불() {
        Long nuRefundNo = 1L;

        doNothing().when(nuRefundService).refund(nuRefundNo);

        verify(nuRefundService, times(1)).refund(nuRefundNo);
    }

    @Test
    void 환불_취소() {
        Long nuRefundNo = 1L;

        doNothing().when(nuRefundService).cancelRefund(nuRefundNo);

        verify(nuRefundService, times(1)).cancelRefund(nuRefundNo);
    }

    @Test
    void 환불_조회() {
        Long nuRefundNo = 1L;

        when(nuRefundService.getNuRefund(nuRefundNo))
                .thenReturn(nuRefund);

        NuRefund getNuRefund = nuRefundService.getNuRefund(nuRefundNo);

        assertEquals(getNuRefund.getNuRefundNo(), nuRefundNo);
        assertEquals(getNuRefund.getNuRefundNo(), nuRefund.getNuRefundNo());
        assertEquals(getNuRefund.getNuRefundYn(), nuRefund.getNuRefundYn());
    }

}
