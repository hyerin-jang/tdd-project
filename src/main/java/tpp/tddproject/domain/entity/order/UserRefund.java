package tpp.tddproject.domain.entity.order;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserRefund {

    @Id
    private Long refundNo;

    private String refundYn;
}
