package tdd.tddproject.domain.entity.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import tdd.tddproject.vo.user.AddressParam;

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
//@DynamicUpdate
//@DynamicInsert
//https://velog.io/@recordsbeat/DynamicUpdate%EA%B0%80-%EC%99%B8%EC%95%8A%EB%90%82%EB%8D%B0
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

    @Builder
    public Address(String addressZip, String addressCity, String addressStreet, String addressReceiver, String addressPhone){
        this.addressZip = addressZip;
        this.addressCity = addressCity;
        this.addressStreet = addressStreet;
        this.addressReceiver = addressReceiver;
        this.addressPhone = addressPhone;
    }

    public void update(AddressParam param){
        if (StringUtils.hasText(param.getAddressStreet())){
            this.addressStreet = param.getAddressStreet();
        }
        if (StringUtils.hasText(param.getAddressZip())){
            this.addressZip = param.getAddressZip();
        }
        if (StringUtils.hasText(param.getAddressCity())){
            this.addressCity = param.getAddressCity();
        }
        if (StringUtils.hasText(param.getAddressReceiver())){
            this.addressReceiver = param.getAddressReceiver();
        }
        if (StringUtils.hasText(param.getAddressPhone())){
            this.addressPhone = param.getAddressPhone();
        }



    }
}
