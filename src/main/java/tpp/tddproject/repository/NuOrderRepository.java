package tpp.tddproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpp.tddproject.domain.entity.nu.NuOrder;

@Repository
public interface NuOrderRepository extends JpaRepository<NuOrder, Long> {
}
