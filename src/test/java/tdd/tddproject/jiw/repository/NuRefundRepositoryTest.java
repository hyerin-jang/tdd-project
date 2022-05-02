package tdd.tddproject.jiw.repository;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tdd.tddproject.domain.entity.nu.NuRefund;
import tdd.tddproject.inwoo.repository.NuRefundRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@DataJpaTest
public class NuRefundRepositoryTest {

    @Autowired NuRefundRepository nuRefundRepository;

    List<NuRefund> nuRefundList;

    @BeforeEach
    public void setUp() {
        nuRefundList = new ArrayList<>() {{
            add(NuRefund.builder()
                    .nuRefundNo(1L)
                    .nuRefundYn("N")
                    .build());
            add(NuRefund.builder()
                    .nuRefundNo(2L)
                    .nuRefundYn("N")
                    .build());
            add(NuRefund.builder()
                    .nuRefundNo(3L)
                    .nuRefundYn("N")
                    .build());
        }};

        nuRefundList.forEach(refund -> nuRefundRepository.save(refund));
    }

    //환불 테스트 케이스
    //1. 환불
    //2. 환불 취소
    //3. 환불 조회?

    @Test
    void 환불() {
        NuRefund nuRefund = nuRefundRepository.findById(1L)
                .orElseThrow(NoSuchElementException::new);
        nuRefund.setRefund();

        nuRefundRepository.save(nuRefund);
        NuRefund getNuRefund = nuRefundRepository.findById(1L)
                .orElseThrow(NoSuchElementException::new);

        assertEquals(getNuRefund.getNuRefundNo(), nuRefund.getNuRefundNo());
        assertEquals(getNuRefund.getNuRefundYn(), nuRefund.getNuRefundYn());
    }

    @Test
    void 환불_취소() {

    }

}

