package tdd.tddproject.domain.entity.nu;

import javax.persistence.*;

@Entity
public class NuRefund {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nuRefundNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nu_order_no")
    private NuOrder nuOrder;

    @Column(length = 1, nullable = false)
    private String nuRefundYn;

    @PrePersist
    public void prePersistYn() {
        this.nuRefundYn = this.nuRefundYn == null
                ? "N"
                : this.nuRefundYn;
    }
}
