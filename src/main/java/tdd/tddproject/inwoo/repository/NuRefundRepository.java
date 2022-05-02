package tdd.tddproject.inwoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdd.tddproject.domain.entity.nu.NuRefund;

import java.util.Optional;

@Repository
public interface NuRefundRepository extends JpaRepository<NuRefund, Long> {

    Optional<NuRefund> findByNuRefundNoAndNuRefundYn(Long nuRefundNo, String nuRefundYn);

}
