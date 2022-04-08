package tpp.tddproject.domain.entity.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long categoryNo;

    private String categoryName;

    private Long mainCategoryNo;

    private String mainCategoryName;

    private Long subCategoryNo;

    private String subCategoryName;
}
