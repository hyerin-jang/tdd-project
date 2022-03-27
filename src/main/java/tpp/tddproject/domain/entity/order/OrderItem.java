package tpp.tddproject.domain.entity.order;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderItem {

    @Id
    private Long orderItemNo;

    private int orderItemCount;

    private int orderItemPrice;

    private String orderItemStatus;

    private String orderItemRefund;
}
