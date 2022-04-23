package tdd.tddproject.jiw.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tdd.tddproject.domain.entity.nu.NuDelivery;
import tdd.tddproject.domain.entity.nu.NuOrder;
import tdd.tddproject.domain.entity.nu.NuOrderItem;
import tdd.tddproject.domain.entity.nu.NuRefund;
import tdd.tddproject.inwoo.repository.NuOrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

//@SpringBootTest
//@AutoConfigureTestDatabase DB 설정을 통해
@DataJpaTest //order 생략가능한 테스트 어노테이션
public class NuOrderRepositoryTest {    //TODO H2 실행시켜 테스트

    @Autowired
    NuOrderRepository nuOrderRepository;

    List<NuOrder> nuOrderList;

    @BeforeEach
    public void setUp() {
        NuDelivery nuDelivery = NuDelivery.builder()
                .nuDeliveryNo(1L)
                .nuDeliveryName("jiw")
                .nuDeliveryPhone("123456789")
                .nuDeliveryCity("어느도시")
                .nuDeliveryDecode("어디어디")
                .nuDeliveryDate(LocalDate.now().toString())
                .build();
        NuOrderItem nuOrderItem = NuOrderItem.builder()
                .nuOrderItemNo(1L)
                .nuOrderItemCount(1)
                .nuOrderItemPrice(1000)
                .nuOrderItemStatus("N")
                .nuOrderItemRefund("N")
                .build();
        NuRefund nuRefund = NuRefund.builder()
                .nuRefundNo(1L)
                .nuRefundYn("Y")
                .build();

        NuOrder nuOrder = NuOrder.builder()
                .nuOrderNo(1L)
                .nuOrderDate(LocalDate.now().toString())
                .nuDeliveries(new ArrayList<>() {{
                    add(nuDelivery);
                }})
                .nuOrderItems(new ArrayList<>() {{
                    add(nuOrderItem);
                }})
                .nuRefunds(new ArrayList<>() {{
                    add(nuRefund);
                }})
                .build();

        nuOrderList = new ArrayList<>() {{
            add(nuOrder);
        }};

        nuOrderList.forEach(order -> nuOrderRepository.save(order));
    }

    @Test
    void 리스트_조회_테스트() {
        List<NuOrder> all = nuOrderRepository.findAll();

        assertEquals(all.size(), this.nuOrderList.size());
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

        assertEquals(newNuOrder.getNuOrderNo(), getNuOrder.getNuOrderNo());
    }

    //TODO 동시 테스트에 대한 무결성 확인
    @Test
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
    void 삭제_테스트() {
        Long nuOrderId = 1L;

        nuOrderRepository.deleteById(nuOrderId);
        List<NuOrder> all = nuOrderRepository.findAll();

        assertEquals(all.size(), 0);
    }

}
