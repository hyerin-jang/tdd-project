package tdd.tddproject.rin.category.mapper;

import org.mapstruct.Mapper;
import tdd.tddproject.domain.entity.item.Category;
import tdd.tddproject.global.mapper.GenericMapper;
import tdd.tddproject.rin.category.dto.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends GenericMapper<CategoryDto, Category> {

}
