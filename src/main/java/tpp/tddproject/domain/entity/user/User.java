package tpp.tddproject.domain.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import tpp.tddproject.domain.entity.cart.Cart;
import tpp.tddproject.domain.entity.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * fileName    : User
 * author      : hyechan
 * date        : 2022/03/26
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/03/26 4:47 오후  hyechan        최초 생성
 */
@Entity @Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
public class User {

    @Id @Column(name = "USER_NO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userNo;

    @Column(name = "USER_ID", nullable = false, length = 30)
    private String userId;

    @Column(name = "USER_PW")
    private String userPw;

    @Column(name = "USER_NAME", nullable = false, length = 50)
    private String userName;

    @Column(name = "USER_EMAIL", nullable = false, length = 50)
    private String userEmail;

    @Column(name = "USER_PHONE", length = 20)
    private String userPhone;

    @Column(name = "USER_TERMS", nullable = false, length = 2)
    private String userTerms;

    @Column(name = "USER_YN", nullable = false, length = 2)
    private String userYn;

    // == 연관관계 맵핑 == /
    //1:1 ADDRESS
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="ADDRESS_ID")
    private Address address;
    //다:1 ROLE
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ROLE_NO", nullable = false)
    private Role Role;
    //1:1 ADMIN
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="ADMIN_NO")
    private Admin admin;
    //1:다 ORDER
    @OneToMany(mappedBy = "User")
    private List<Order> OrderList = new ArrayList<>();
    //1:다 CART
    @OneToMany(mappedBy = "User")
    private List<Cart> cartList = new ArrayList<>();
}
