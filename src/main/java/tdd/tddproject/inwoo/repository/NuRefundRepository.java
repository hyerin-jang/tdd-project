package tdd.tddproject.inwoo.repository;

import org.aspectj.weaver.ast.And;
import org.springframework.data.jpa.repository.JpaRepository;
import tdd.tddproject.domain.entity.nu.NuRefund;

import java.util.Optional;

public interface NuRefundRepository extends JpaRepository<NuRefund, Long> {

    Optional<NuRefund> findByIdAAndNuRefundYn(Long aLong, String nuRefundYn);

}
