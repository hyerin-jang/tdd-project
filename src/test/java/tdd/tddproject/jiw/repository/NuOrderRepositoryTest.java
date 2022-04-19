package tdd.tddproject.jiw.repository;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tdd.tddproject.domain.entity.nu.NuDelivery;
import tdd.tddproject.domain.entity.nu.NuOrder;
import tdd.tddproject.domain.entity.nu.NuOrderItem;
import tdd.tddproject.domain.entity.nu.NuRefund;
import tdd.tddproject.inwoo.repository.NuOrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@SpringBootTest
public class NuOrderRepositoryTest {

    @Autowired
    NuOrderRepository nuOrderRepository;

    List<NuOrder> nuOrderList;

    @BeforeEach
    public void setUp() {
        NuOrder nuOrder = NuOrder.builder()
                .nuOrderNo(1L)
                .nuOrderDate(LocalDate.now().toString())
                .nuDeliveries(new ArrayList<>() {{
                    add(new NuDelivery());
                }})
                .nuOrderItems(new ArrayList<>() {{
                    add(new NuOrderItem());
                }})
                .nuRefunds(new ArrayList<>() {{
                    add(new NuRefund());
                }})
                .build();

        nuOrderList = new ArrayList<>();

        nuOrderList.forEach(order -> nuOrderRepository.save(nuOrder));

    }

    @Test
    void 리스트_조회_테스트() {
        List<NuOrder> all = nuOrderRepository.findAll();

        assertEquals(all.size(), nuOrderList.size());
    }

    @Test
    void 조회_테스트() {
        Long nuOrderId = 1L;

        NuOrder getNuOrder = nuOrderRepository.findById(nuOrderId)
                .orElseThrow(NoSuchElementException::new);

        assertEquals(getNuOrder.getNuOrderNo(), nuOrderId);
    }

    @Test
    void 생성_테스트() {

    }

    @Test
    void 수정_테스트() {

    }

    @Test
    void 삭제_테스트() {

    }

}
