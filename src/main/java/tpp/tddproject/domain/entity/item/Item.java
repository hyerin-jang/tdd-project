package tpp.tddproject.domain.entity.item;

import tpp.tddproject.domain.entity.nu.NuOrderItem;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long itemNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_NO")
    private Category category;

    private String itemComp;

    private String itemName;

    private int itemPrice;

    private int itemStock;

    private String itemDesc;

    private int itemSale;

    private String itemStatus;

    @OneToMany(mappedBy = "nuOrderItemNo")
    private List<NuOrderItem> nuOrderItems;

}
