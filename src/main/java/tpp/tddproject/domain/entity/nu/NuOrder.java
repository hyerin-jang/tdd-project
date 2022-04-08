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

    @OneToMany(mappedBy = "nu_delivery")
    private List<NuDelivery> nuDeliveries;

    @OneToMany(mappedBy = "nu_order_item")
    private List<NuOrderItem> nuOrderItems;

    @OneToMany(mappedBy = "nu_refund")
    private List<NuRefund> nuRefunds = new ArrayList<>();

}
