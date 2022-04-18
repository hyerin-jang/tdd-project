package tdd.tddproject.jiw.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.tddproject.domain.entity.nu.NuOrder;
import tdd.tddproject.inwoo.service.NuOrderService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NuOrderServiceTest {

    @Mock
    NuOrderService nuOrderService;

    List<NuOrder> nuOrders;

    @BeforeEach
    void setUp() {
        nuOrders = new ArrayList<>() {{
            add(new NuOrder());
            add(new NuOrder());
        }};
    }


    @Test
    void 리스트_조회() {
        when(nuOrderService.getNuOrderList())
                .thenReturn(nuOrders);

        List<NuOrder> list = nuOrderService.getNuOrderList();

        assertEquals(list.size(), nuOrders.size());
    }

}
