package tpp.tddproject.domain.entity.cart;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cart {

    @Id
    private Long cartNo;

    private int cartCount;
}
