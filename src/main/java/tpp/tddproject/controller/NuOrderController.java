package tpp.tddproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tpp.tddproject.domain.entity.nu.NuOrder;
import tpp.tddproject.service.impl.NuOrderServiceImpl;

@RestController
@RequiredArgsConstructor
public class NuOrderController {

    private final NuOrderServiceImpl nuOrderService;

    @GetMapping("/")
    public void test() {
        nuOrderService.test();
    }

    @PostMapping("/order")
    public void order(NuOrder nuOrder) {
        nuOrderService.order(nuOrder);
    }

}
