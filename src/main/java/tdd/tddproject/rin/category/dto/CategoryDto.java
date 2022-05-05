package tdd.tddproject.rin.category.dto;

import lombok.*;

@Data
@Getter @Setter
public class CategoryDto {

    private Long categoryNo;

    private String categoryName;

    private Long mainCategoryNo;

    private String mainCategoryName;

    private Long subCategoryNo;

    private String subCategoryName;

    @Builder
    public CategoryDto(Long categoryNo, String categoryName, Long mainCategoryNo,
                       String mainCategoryName, Long subCategoryNo, String subCategoryName) {

        this.categoryNo = categoryNo;
        this.categoryName = categoryName;
        this.mainCategoryNo = mainCategoryNo;
        this.mainCategoryName = mainCategoryName;
        this.subCategoryNo = subCategoryNo;
        this.subCategoryName = subCategoryName;

    }

}
