package tdd.tddproject.jiw.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.tddproject.domain.entity.nu.NuOrder;
import tdd.tddproject.inwoo.service.NuOrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NuOrderServiceTest {

    @Mock
    NuOrderService nuOrderService;

    NuOrder nuOrder;
    List<NuOrder> nuOrders;

    @BeforeEach
    void setUp() {
        nuOrders = new ArrayList<>() {{
            add(NuOrder.builder()
                    .build());
            add(NuOrder.builder()
                    .build());
        }};

        nuOrder = NuOrder.builder()
                .nuOrderNo(1L)
                .build();
    }

    @Test
    void 리스트_조회() {
        when(nuOrderService.getNuOrderList())
                .thenReturn(nuOrders);

        List<NuOrder> list = nuOrderService.getNuOrderList();

        assertEquals(list.size(), nuOrders.size());
    }

    @Test
    void 단일_조회() {
        Long nuOrderId = 1L;
        when(nuOrderService.getNuOrder(nuOrderId))
                .thenReturn(java.util.Optional.ofNullable(nuOrder));

        NuOrder getNuOrder = nuOrderService.getNuOrder(nuOrderId)
                .orElseThrow(NoSuchElementException::new);

        assertEquals(nuOrder, getNuOrder);
    }

    @Test
    void 수정() {
        Long nuOrderId = 1L;
        String nuOrderDate = LocalDate.now()
                .toString();
        NuOrder newNuOrder = NuOrder.builder()
                .nuOrderNo(nuOrderId)
                .nuOrderDate(nuOrderDate)
                .build();

        //이미 고립된 mock더미가 실행시키므로 굳이 필요한지..?
        //없어도 상관없음
        doNothing().when(nuOrderService).saveNuOrder(newNuOrder);
        nuOrderService.saveNuOrder(newNuOrder);

        verify(nuOrderService, times(1)).saveNuOrder(newNuOrder);
    }

    @Test
    void 삭제() {
        Long nuOrderId = 1L;

        //이미 고립된 mock더미가 실행시키므로 굳이 필요한지..?
        //없어도 상관없음
        doNothing().when(nuOrderService).deleteNuOrder(nuOrderId);
        nuOrderService.deleteNuOrder(nuOrderId);

        verify(nuOrderService, times(1)).deleteNuOrder(nuOrderId);
    }

}
