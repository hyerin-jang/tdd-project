package tpp.tddproject.domain.entity.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderItemNo;

    private int orderItemCount;

    private int orderItemPrice;

    private String orderItemStatus;

    private String orderItemRefund;
}
