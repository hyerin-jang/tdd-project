package tdd.tddproject.rin.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdd.tddproject.domain.entity.item.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
