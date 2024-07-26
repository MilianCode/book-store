package com.miliancode.service.impl;

import com.miliancode.dto.category.CategoryDto;
import com.miliancode.dto.category.CreateCategoryRequestDto;
import com.miliancode.exception.EntityNotFoundException;
import com.miliancode.mapper.CategoryMapper;
import com.miliancode.model.Category;
import com.miliancode.repository.CategoryRepository;
import com.miliancode.service.CategoryService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    @Override
    public CategoryDto save(CreateCategoryRequestDto requestDto) {
        Category category = mapper.toModel(requestDto);
        return mapper.toDto(categoryRepository.save(category));
    }

    @Transactional
    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto requestDto) {
        Category category = categoryRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Cant find "
                                + "category by id = " + id));
        mapper.updateCategoryFromDto(requestDto, category);
        return mapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cant find category by id = " + id));
        return mapper.toDto(category);
    }

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(mapper::toDto)
                .toList();
    }
}
