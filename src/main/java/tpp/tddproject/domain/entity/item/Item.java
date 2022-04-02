package tpp.tddproject.domain.entity.item;

import javax.persistence.*;

@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long itemNo;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_NO")
    private Category category;

    private String itemComp;

    private String itemName;

    private int itemPrice;

    private int itemStock;

    private String itemDesc;

    private int itemSale;

    private String itemStatus;

}
