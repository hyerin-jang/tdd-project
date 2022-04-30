package tdd.tddproject.domain.entity.item;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryNo;

    @Column(length = 20)
    private String categoryName;

    private Long mainCategoryNo;

    @Column(length = 20)
    private String mainCategoryName;

    private Long subCategoryNo;

    @Column(length = 20)
    private String subCategoryName;
}
