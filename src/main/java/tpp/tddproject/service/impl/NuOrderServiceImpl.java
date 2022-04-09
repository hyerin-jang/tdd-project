package tpp.tddproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tpp.tddproject.domain.entity.nu.NuOrder;
import tpp.tddproject.repository.NuOrderRepository;

@Service
@RequiredArgsConstructor
public class NuOrderServiceImpl {

    private final NuOrderRepository nuOrderRepository;

    public void test() {}

    public void order(NuOrder nuOrder) {

    }

}
