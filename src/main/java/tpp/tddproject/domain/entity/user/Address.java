package tpp.tddproject.domain.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * fileName    : Address
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
public class Address {

    @Id @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    @Column(name = "ADDRESS_ZIP", nullable = false, length = 20)
    private String addressZip;

    @Column(name = "ADDRESS_CITY", nullable = false, length = 50)
    private String addressCity;

    @Column(name = "ADDRESS_STREET", length = 50)
    private String addressStreet;

    @Column(name = "ADDRESS_RECEIVER", nullable = false, length = 20)
    private String addressReceiver;

    @Column(name = "ADDRESS_PHONE", nullable = false, length = 20)
    private String addressPhone;

    // == 연관관계 맵핑 == /
    //1:1 유저
    @OneToOne(mappedBy="address", fetch = FetchType.LAZY)
    private User user;
}
