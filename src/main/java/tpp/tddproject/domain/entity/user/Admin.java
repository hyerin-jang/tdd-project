package tpp.tddproject.domain.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * fileName    : Admin
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
public class Admin {
    
    @Id @Column(name = "ADMIN_NO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminNo;

    @Column(name = "ADMIN_NAME", length = 20)
    private String adminName;

    @Column(name = "ADMIN_DEPT", length = 50)
    private String adminDept;

    @Column(name = "ADMIN_LEVEL", length= 30)
    private String adminLevel;

    @Column(name = "ADMIN_PHONE", length = 20)
    private String adminPhone;

    // == 연관관계 맵핑 == /
    //1:1 유저
    @OneToOne(mappedBy="admin", fetch = FetchType.LAZY)
    private User user;
}
