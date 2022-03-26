package tpp.tddproject.domain.entity.nu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NuOrder {

    @Id
    private Long nuOrderNo;

    @Column(nullable = false)
    private String nuOrderDate;

}
