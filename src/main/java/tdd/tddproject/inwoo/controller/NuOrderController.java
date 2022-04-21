package tdd.tddproject.inwoo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tdd.tddproject.domain.entity.nu.NuOrder;
import tdd.tddproject.inwoo.dto.ResponseDataDto;
import tdd.tddproject.inwoo.service.NuOrderService;
import tdd.tddproject.inwoo.service.ResponseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class NuOrderController {

    private final NuOrderService nuOrderService;
    private final ResponseService responseService;

    /**
     * 테스트 완료 후 삭제
     */
    @GetMapping("/test")
    public void test() {
        nuOrderService.test();
    }

    @GetMapping
    public List<NuOrder> getNuOrderList() {
        return nuOrderService.getNuOrderList();
    }

    @GetMapping("/{nuOrderNo}")
    public Optional<NuOrder> getNuOrder(@PathVariable Long nuOrderNo) {
        return nuOrderService.getNuOrder(nuOrderNo);
    }

    @PostMapping
    public void saveNuOrder(@RequestBody NuOrder nuOrder) {
        nuOrderService.saveNuOrder(nuOrder);
    }

    @PutMapping("/{nuOrderNo}}")
    public void updateNuOrder(@PathVariable Long nuOrderNo, @RequestBody NuOrder nuOrder) {
        nuOrderService.saveNuOrder(nuOrder);
    }

    @DeleteMapping("/{nuOrderNo}}")
    public void deleteNuOrder(@PathVariable Long nuOrderNo) {
        nuOrderService.deleteNuOrder(nuOrderNo);
    }

}
