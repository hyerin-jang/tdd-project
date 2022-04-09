package tpp.tddproject.domain.entity.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import tpp.tddproject.domain.entity.nu.NuOrderItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private List<NuOrderItem> nuOrderItems = new ArrayList<>();

    @Builder
    public Item (String itemComp, String itemName, int itemPrice, int itemStock,
                String itemDesc, int itemSale, String itemStatus) {

        this.itemComp = itemComp;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        this.itemDesc = itemDesc;
        this.itemSale = itemSale;
        this.itemStatus = itemStatus;

    }

}
