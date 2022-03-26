package tpp.tddproject.domain.entity.nu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NuRefund {

    @Id
    private Long nuRefundNo;

    @Column(nullable = false)
    private Long nuOrderNo;

    @Column(nullable = false)
    private char nuRefundYn;

}
