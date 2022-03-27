package tpp.tddproject.domain.entity.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    private Long itemNo;

    private String itemComp;

    private String itemName;

    private int itemPrice;

    private int itemStock;

    private String itemDesc;

    private int itemSale;

    private String itemStatus;

}
