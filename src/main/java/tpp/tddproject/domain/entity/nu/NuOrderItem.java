package tpp.tddproject.domain.entity.nu;

import javax.persistence.*;

@Entity
public class NuOrderItem {

    @Id
    private Long nuOrderItemNo;

    @Column(nullable = false)
    private Long productNo;

    @ManyToOne
    @JoinColumn(name = "nu_order_no")
    private NuOrder nuOrder;

    @Column(nullable = false)
    private int nuOrderItemCount;

    @Column(nullable = false)
    private int nuOrderItemPrice;

    @Column(nullable = false)
    private char nuOrderItemStatus;

    @Column(nullable = false)
    private char nuOrderItemRefund;

}
