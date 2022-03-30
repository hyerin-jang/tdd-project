package tpp.tddproject.domain.entity.cart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cartNo;

    private int cartCount;
}