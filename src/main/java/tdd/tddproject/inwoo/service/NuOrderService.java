package tpp.tddproject.inwoo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tpp.tddproject.domain.entity.nu.NuOrder;
import tpp.tddproject.inwoo.dto.ResponseDataDto;
import tpp.tddproject.inwoo.repository.NuOrderRepository;

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
