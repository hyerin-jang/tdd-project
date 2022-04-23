package tdd.tddproject.domain.entity.nu;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public NuDelivery(Long nuDeliveryNo, NuOrder nuOrder, String nuDeliveryName, String nuDeliveryPhone, String nuDeliveryCity, String nuDeliveryStreet, String nuDeliveryDecode, String nuDeliveryDate) {
        this.nuDeliveryNo = nuDeliveryNo;
        this.nuOrder = nuOrder;
        this.nuDeliveryName = nuDeliveryName;
        this.nuDeliveryPhone = nuDeliveryPhone;
        this.nuDeliveryCity = nuDeliveryCity;
        this.nuDeliveryStreet = nuDeliveryStreet;
        this.nuDeliveryDecode = nuDeliveryDecode;
        this.nuDeliveryDate = nuDeliveryDate;
    }
}
