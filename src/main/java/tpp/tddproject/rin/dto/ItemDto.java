package tpp.tddproject.rin.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import tpp.tddproject.domain.entity.item.Category;
import tpp.tddproject.domain.entity.nu.NuOrderItem;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
public class ItemDto {

    private Long itemNo;

    private Category category;

    private String itemComp;

    private String itemName;

    private int itemPrice;

    private int itemStock;

    private String itemDesc;

    private int itemSale;

    private String itemStatus;

    private List<NuOrderItem> noOrderItem = new ArrayList<>();

    @Builder
    public ItemDto (String itemComp, String itemName, int itemPrice, int itemStock,
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
