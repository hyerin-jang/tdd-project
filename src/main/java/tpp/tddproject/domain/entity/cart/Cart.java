package tpp.tddproject.domain.entity.cart;

import tpp.tddproject.domain.entity.item.Item;
import tpp.tddproject.domain.entity.user.User;

import javax.persistence.*;

@Entity
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cartNo;

    @ManyToOne
    @JoinColumn(name = "USER_NO")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ITEM_NO")
    private Item item;

    private int cartCount;
}