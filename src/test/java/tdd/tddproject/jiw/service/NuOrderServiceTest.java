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
import java.util.Optional;

import static org.junit.Assert.*;
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

        doReturn(newNuOrder).when(nuOrderService).saveNuOrder(newNuOrder);
        doNothing().when(nuOrderService).saveNuOrder(newNuOrder);
        //실제 메서드 반환 받는방법
//        NuOrder getNuOrder = nuOrderService.saveNuOrder(newNuOrder);
//        assertEquals();
    }

    @Test
    void 삭제() {

    }

}
