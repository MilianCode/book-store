package com.miliancode.service;

import com.miliancode.dto.category.CategoryDto;
import com.miliancode.dto.category.CreateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto save(CreateCategoryRequestDto requestDto);

    CategoryDto update(Long id, CreateCategoryRequestDto requestDto);

    void deleteById(Long id);

    CategoryDto findById(Long id);

    List<CategoryDto> findAll(Pageable pageable);
}
