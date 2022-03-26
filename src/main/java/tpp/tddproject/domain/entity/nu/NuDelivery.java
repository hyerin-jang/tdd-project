package tpp.tddproject.domain.entity.nu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NuDelivery {

    @Id
    private Long nuDeliveryNo;

    private Long nuOrderNo;

    @Column(length = 20, nullable = false)
    private String nuDeliveryName;

    @Column(length = 20, nullable = false)
    private String nuDeliveryPhone;

    @Column(length = 50, nullable = false)
    private String nuDeliveryCity;

    @Column(length = 50)
    private String nuDeliveryStreet;

    @Column(length = 20, nullable = false)
    private String nuDeliveryDecode;

    @Column(nullable = false)
    private String nuDeliveryDate;

}
