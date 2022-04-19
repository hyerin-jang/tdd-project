package tdd.tddproject.domain.entity.nu;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.tddproject.vo.nu.NuOrderVo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NuOrder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nuOrderNo;

    @Column(nullable = false)
    private String nuOrderDate;

    @OneToMany(mappedBy = "nuDeliveryNo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NuDelivery> nuDeliveries = new ArrayList<>();

    @OneToMany(mappedBy = "nuOrderItemNo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NuOrderItem> nuOrderItems = new ArrayList<>();

    @OneToMany(mappedBy = "nuRefundNo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NuRefund> nuRefunds = new ArrayList<>();

    public NuOrderVo toVo() {
        return NuOrderVo.builder()
                .nuOrderNo(nuOrderNo)
                .nuOrderDate(nuOrderDate)
                .build();
    }

    @Builder
    public NuOrder(Long nuOrderNo, String nuOrderDate, List<NuDelivery> nuDeliveries, List<NuOrderItem> nuOrderItems, List<NuRefund> nuRefunds) {
        this.nuOrderNo = nuOrderNo;
        this.nuOrderDate = nuOrderDate;
        this.nuDeliveries = nuDeliveries;
        this.nuOrderItems = nuOrderItems;
        this.nuRefunds = nuRefunds;
    }
}
