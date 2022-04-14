package tpp.tddproject.domain.entity.nu;

import lombok.Getter;
import tpp.tddproject.vo.nu.NuOrderVo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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
