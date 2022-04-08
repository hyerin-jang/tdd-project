package tpp.tddproject.domain.entity.nu;

import javax.persistence.*;

@Entity
public class NuRefund {

    @Id
    private Long nuRefundNo;

    @ManyToOne
    @JoinColumn(name = "nu_order_no")
    private NuOrder nuOrder;

    @Column(nullable = false)
    private char nuRefundYn;

}
