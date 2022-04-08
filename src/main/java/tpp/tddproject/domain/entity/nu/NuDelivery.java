package tpp.tddproject.domain.entity.nu;

import javax.persistence.*;

@Entity
public class NuDelivery {

    @Id
    private Long nuDeliveryNo;

    @ManyToOne
    @JoinColumn(name = "nu_order_no")
    private NuOrder nuOrder;

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
