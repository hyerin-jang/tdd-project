package tdd.tddproject.domain.entity.nu;

import lombok.Getter;
import tdd.tddproject.domain.entity.item.Item;

import javax.persistence.*;

@Entity
@Getter
public class NuOrderItem {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nuOrderItemNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_no")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nu_order_no")
    private NuOrder nuOrder;

    @Column(nullable = false)
    private int nuOrderItemCount;

    @Column(nullable = false)
    private int nuOrderItemPrice;

    @Column(length = 1, nullable = false)
    private String nuOrderItemStatus;

    @Column(length = 1, nullable = false)
    private String nuOrderItemRefund;

    @PrePersist
    public void prePersistYn() {
        this.nuOrderItemStatus = this.nuOrderItemStatus == null
                ? "N"
                : this.nuOrderItemStatus;

        this.nuOrderItemRefund = this.nuOrderItemRefund == null
                ? "N"
                : this.nuOrderItemRefund;
    }
}
