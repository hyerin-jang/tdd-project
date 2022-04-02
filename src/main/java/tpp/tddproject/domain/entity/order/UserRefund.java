package tpp.tddproject.domain.entity.order;

import javax.persistence.*;

@Entity
public class UserRefund {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long refundNo;

    @OneToOne
    @JoinColumn(name = "ORDER_ITEM_NO")
    private OrderItem orderItem;

    private String refundYn;
}
