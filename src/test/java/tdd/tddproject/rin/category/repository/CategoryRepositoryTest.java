package tdd.tddproject.rin.category.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tdd.tddproject.domain.entity.item.Category;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void 카테고리_조회() throws Exception {
        //given
        Category category = Category.builder()
                .categoryNo(1L)
                .categoryName("의류")
                .mainCategoryNo(1L)
                .mainCategoryName("상의")
                .subCategoryNo(1L)
                .subCategoryName("티셔츠")
                .build();

        //when
        Category categorySave = categoryRepository.save(category);

        //then
        assertThat(categorySave.getCategoryNo(), is(1L));

    }
}
