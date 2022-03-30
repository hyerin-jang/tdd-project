package tpp.tddproject.domain.entity.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserRefund {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long refundNo;

    private String refundYn;
}
