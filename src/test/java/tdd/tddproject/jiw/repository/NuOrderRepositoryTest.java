package tdd.tddproject.jiw.repository;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
public class NuOrderRepositoryTest {    //TODO H2 실행시켜 테스트

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

        nuOrderList = new ArrayList<>() {{
            add(nuOrder);
        }};

        nuOrderList.forEach(order -> nuOrderRepository.save(order));
    }

    @Test
    @Order(0)
    void 리스트_조회_테스트() {
        List<NuOrder> all = nuOrderRepository.findAll();

        assertEquals(all.size(), this.nuOrderList.size());
    }

    @Test
    @Order(1)
    void 조회_테스트() {
        Long nuOrderId = 1L;

        NuOrder getNuOrder = nuOrderRepository.findById(nuOrderId)
                .orElseThrow(NoSuchElementException::new);

        assertEquals(getNuOrder.getNuOrderNo(), nuOrderId);
    }

    @Test
    @Order(2)
    void 생성_테스트() {
        Long nuOrderId = 2L;
        String nuOrderDate = LocalDate.now()
                .toString();
        NuOrder newNuOrder = NuOrder.builder()
                .nuOrderNo(nuOrderId)
                .nuOrderDate(nuOrderDate)
                .build();

        nuOrderRepository.save(newNuOrder);
        NuOrder getNuOrder = nuOrderRepository.findById(nuOrderId)
                .orElseThrow(NoSuchElementException::new);

        assertEquals(newNuOrder, getNuOrder);
    }

    @Test
    @Order(3)
    void 수정_테스트() {
        Long nuOrderId = 1L;
        NuOrder getNuOrder = nuOrderRepository.findById(nuOrderId)
                .orElseThrow(NoSuchElementException::new);
        String now = LocalDate.now()
                .toString();
        getNuOrder = NuOrder.builder()
                .nuOrderNo(nuOrderId)
                .nuOrderDate(now)
                .build();

        nuOrderRepository.save(getNuOrder);
        NuOrder getNuOrder2 = nuOrderRepository.findById(nuOrderId)
                .orElseThrow(NoSuchElementException::new);

        assertNotEquals(getNuOrder, getNuOrder2);
        assertEquals(getNuOrder2.getNuOrderNo(), nuOrderId);
        assertEquals(getNuOrder2.getNuOrderDate(), now);
    }

    @Test
    @Order(4)
    void 삭제_테스트() {
        Long nuOrderId = 1L;

        nuOrderRepository.deleteById(nuOrderId);
        List<NuOrder> all = nuOrderRepository.findAll();

        assertEquals(all.size(), this.nuOrderList.size());
    }

}
