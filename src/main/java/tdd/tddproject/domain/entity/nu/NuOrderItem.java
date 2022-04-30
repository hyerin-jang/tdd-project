package tdd.tddproject.domain.entity.nu;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.tddproject.domain.entity.item.Item;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public NuOrderItem(Long nuOrderItemNo, Item item, NuOrder nuOrder, int nuOrderItemCount, int nuOrderItemPrice, String nuOrderItemStatus, String nuOrderItemRefund) {
        this.nuOrderItemNo = nuOrderItemNo;
        this.item = item;
        this.nuOrder = nuOrder;
        this.nuOrderItemCount = nuOrderItemCount;
        this.nuOrderItemPrice = nuOrderItemPrice;
        this.nuOrderItemStatus = nuOrderItemStatus;
        this.nuOrderItemRefund = nuOrderItemRefund;
    }
}
