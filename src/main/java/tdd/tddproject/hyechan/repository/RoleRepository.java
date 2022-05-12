package tdd.tddproject.hyechan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdd.tddproject.domain.entity.user.Role;
import tdd.tddproject.domain.entity.user.RoleType;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleType roleType);
}