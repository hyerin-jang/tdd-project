package tdd.tddproject.domain.entity.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import tdd.tddproject.domain.entity.nu.NuOrderItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
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
    public Item (Long itemNo, String itemComp, String itemName, int itemPrice, int itemStock,
                String itemDesc, int itemSale, String itemStatus) {

        this.itemNo = itemNo;
        this.itemComp = itemComp;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        this.itemDesc = itemDesc;
        this.itemSale = itemSale;
        this.itemStatus = itemStatus;

    }

}
