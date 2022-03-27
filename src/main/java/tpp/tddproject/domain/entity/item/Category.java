package tpp.tddproject.domain.entity.item;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    private Long categoryNo;

    private String categoryName;

    private Long mainCategoryNo;

    private String mainCategoryName;

    private Long subCategoryNo;

    private String subCategoryName;
}
