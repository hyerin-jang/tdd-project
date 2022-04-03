package tpp.tddproject.domain.entity.order;

import tpp.tddproject.domain.entity.item.Item;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ORDER_ITEM_NO")
    private Long orderItemNo;

    @ManyToOne
    @JoinColumn(name = "ITEM_NO")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_NO")
    private Order order;

    private int orderItemCount;

    private int orderItemPrice;

    private String orderItemStatus;

    private String orderItemRefund;
}
