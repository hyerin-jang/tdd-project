package tpp.tddproject.domain.entity.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tpp.tddproject.domain.entity.user.Admin;
import tpp.tddproject.domain.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * fileName    : Order
 * author      : hyechan
 * date        : 2022/04/01
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/04/01 11:11 오후  hyechan        최초 생성
 */
@Table(name = "orders")
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
public class Order {
    
    @Id @Column(name = "ORDER_NO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderNo;

    @CreatedDate @Column(name = "ORDER_DATE", nullable= false)
    private LocalDateTime orderDate;

    // == 연관관계 맵핑 == /
    // 1:1 배송
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="DELIVERY_NO")
    private Delivery delivery;

    // 1:다 주문_아이템
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList = new ArrayList<>();

    // 다:1 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_NO")
    private User user;

}
