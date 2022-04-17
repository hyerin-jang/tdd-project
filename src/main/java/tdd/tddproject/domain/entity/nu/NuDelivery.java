package tdd.tddproject.domain.entity.nu;

import javax.persistence.*;

@Entity
public class NuDelivery {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nuDeliveryNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nuOrderNo")
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
