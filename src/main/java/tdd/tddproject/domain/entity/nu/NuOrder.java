package tdd.tddproject.domain.entity.nu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.tddproject.vo.nu.NuOrderVo;
import tdd.tddproject.domain.entity.nu.NuOrderItem;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class NuOrder {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nuOrderNo;

    @Column(nullable = false)
    private String nuOrderDate;

    @OneToMany(mappedBy = "nuDeliveryNo")
    private List<NuDelivery> nuDeliveries;

    @OneToMany(mappedBy = "nuOrderItemNo")
    private List<NuOrderItem> nuOrderItems;

    @OneToMany(mappedBy = "nuRefundNo")
    private List<NuRefund> nuRefunds;

    public NuOrderVo toVo() {
        return NuOrderVo.builder()
                .nuOrderNo(nuOrderNo)
                .nuOrderDate(nuOrderDate)
                .build();
    }

}
