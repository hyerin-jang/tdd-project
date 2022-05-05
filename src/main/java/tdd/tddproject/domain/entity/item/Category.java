package tdd.tddproject.domain.entity.item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryNo;

    @Column(nullable=false, length = 20)
    private String categoryName;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable=false)
    private Long mainCategoryNo;

    @Column(nullable=false, length = 20)
    private String mainCategoryName;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subCategoryNo;

    @Column(length = 20)
    private String subCategoryName;

    @Builder
    public Category(Long categoryNo, String categoryName,
                    Long mainCategoryNo, String mainCategoryName,
                    Long subCategoryNo, String subCategoryName) {

        this.categoryNo = categoryNo;
        this.categoryName = categoryName;
        this.mainCategoryNo = mainCategoryNo;
        this.mainCategoryName = mainCategoryName;
        this.subCategoryNo = subCategoryNo;
        this.subCategoryName = subCategoryName;
    }


}
