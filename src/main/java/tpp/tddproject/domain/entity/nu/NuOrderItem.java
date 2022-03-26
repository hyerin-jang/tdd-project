package tpp.tddproject.domain.entity.nu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NuOrderItem {

    @Id
    private Long nuOrderItemNo;

    @Column(nullable = false)
    private Long productNo;

    @Column(nullable = false)
    private Long nuOrderNo;

    @Column(nullable = false)
    private int nuOrderItemCount;

    @Column(nullable = false)
    private int nuOrderItemPrice;

    @Column(nullable = false)
    private char nuOrderItemStatus;

    @Column(nullable = false)
    private char nuOrderItemRefund;

}
