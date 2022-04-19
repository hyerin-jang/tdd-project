package tdd.tddproject.inwoo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tdd.tddproject.domain.entity.nu.NuOrder;
import tdd.tddproject.inwoo.repository.NuOrderRepository;
import tdd.tddproject.inwoo.dto.ResponseDataDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NuOrderService {

    private final NuOrderRepository nuOrderRepository;

    public void test() {}

    public List<NuOrder> getNuOrderList() {
        return nuOrderRepository.findAll();
    }

    public Optional<NuOrder> getNuOrder(Long nuOrderNo) {
        return nuOrderRepository.findById(nuOrderNo);
    }

    public void saveNuOrder(NuOrder nuOrder) {
        nuOrderRepository.save(nuOrder);
    }

    public void deleteNuOrder(Long nuOrderNo) {
        nuOrderRepository.deleteById(nuOrderNo);
    }
}
