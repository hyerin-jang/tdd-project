package tdd.tddproject.rin.category.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.rin.category.dto.CategoryDto;
import tdd.tddproject.rin.category.mapper.CategoryMapper;
import tdd.tddproject.rin.category.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Transactional
    public List<CategoryDto> getAllCategory() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toList());
    }
}
