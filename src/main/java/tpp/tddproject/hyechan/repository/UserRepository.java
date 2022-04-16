package tpp.tddproject.hyechan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import tpp.tddproject.domain.entity.user.User;

/**
 * fileName    : UserService
 * author      : hyechan
 * date        : 2022/04/09
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/04/09 3:45 오후  hyechan        최초 생성
 */
@Repository
public interface UserRepository
        extends JpaRepository<User, Long>,
        UserRepositorySupport, QuerydslPredicateExecutor<User> {


}
