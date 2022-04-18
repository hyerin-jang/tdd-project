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
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
public class Role {

    @Id @Column(name = "ROLE_NO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleNo;

    @Column(name = "ROLE_NAME")
    @Enumerated(EnumType.STRING)
    private RoleType roleName;

    // == 연관관계 맵핑 == /
    //1:다 유저
    @OneToMany(mappedBy="role")
    private List<User> userList = new ArrayList<>();

}
