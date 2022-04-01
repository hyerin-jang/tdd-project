package tpp.tddproject.domain.entity.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tpp.tddproject.domain.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * fileName    : Delivery
 * author      : hyechan
 * date        : 2022/04/01
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/04/01 11:12 오후  hyechan        최초 생성
 */
@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
public class Delivery {
    
    @Id @Column(name = "DELIVERY_NO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deliveryNo;

    @Column(name = "DELIVERY_ZIP", nullable = false, length = 20)
    private String deliveryZip;

    @Column(name = "DELIVERY_STREET", nullable = false, length = 50)
    private String deliveryStreet;

    @Column(name = "DELIVERY_ADDRESS", length = 50)
    private String deliveryAddress;

    @Column(name = "DELIVERY_RECEIVER", nullable = false, length = 20)
    private String deliveryReceiver;

    @Column(name = "DELIVERY_PHONE", nullable = false, length = 20)
    private String deliveryPhone;

    @Column(name = "DELIVERY_DATE")
    private LocalDateTime deliveryDate;

    @Column(name = "DELIVERY_DONEDATE")
    private LocalDateTime deliveryDonedate;

    // == 연관관계 맵핑 == /
    // 1:1 주문
    @OneToOne(mappedBy="Delivery", fetch = FetchType.LAZY)
    private Order order;
}
