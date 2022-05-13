package tdd.tddproject.domain.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * fileName    : Role
 * author      : hyechan
 * date        : 2022/03/26
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/03/26 4:47 오후  hyechan        최초 생성
 */
@Entity @Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "ROLE_SEQ_GENERATOR",
        sequenceName = "ROLE_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
public class Role {

    @Id @Column(name = "ROLE_NO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ_GENERATOR")
    private Long roleNo;

    @Column(name = "ROLE_NAME")
    @Enumerated(EnumType.STRING)
    private RoleType roleName;

    // == 연관관계 맵핑 == /
    //1:다 유저
    @OneToMany(mappedBy="role")
    private List<User> userList = new ArrayList<>();

    public Role(RoleType roleType){
        this.roleName = roleType;
    }


}
