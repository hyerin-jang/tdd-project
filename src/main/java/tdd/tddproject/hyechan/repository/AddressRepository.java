package tdd.tddproject.hyechan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdd.tddproject.domain.entity.user.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
