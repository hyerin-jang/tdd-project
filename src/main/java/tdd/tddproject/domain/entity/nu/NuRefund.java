package tdd.tddproject.domain.entity.nu;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public NuRefund(Long nuRefundNo, NuOrder nuOrder, String nuRefundYn) {
        this.nuRefundNo = nuRefundNo;
        this.nuOrder = nuOrder;
        this.nuRefundYn = nuRefundYn;
    }

    public void setRefund() {
        this.nuRefundYn = "Y";
    }

    public void setRefundCancel() {
        this.nuRefundYn = "N";
    }
}
