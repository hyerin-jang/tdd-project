package tpp.tddproject.domain.entity.nu;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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

}
