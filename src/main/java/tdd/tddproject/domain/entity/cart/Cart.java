package tdd.tddproject.domain.entity.cart;

import tdd.tddproject.domain.entity.item.Item;
import tdd.tddproject.domain.entity.user.User;

import javax.persistence.*;

@Entity
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cartNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NO")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_NO")
    private Item item;

    private int cartCount;
}