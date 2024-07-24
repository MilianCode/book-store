package com.miliancode.mapper;

import com.miliancode.config.MapStructConfig;
import com.miliancode.dto.category.CategoryDto;
import com.miliancode.dto.category.CreateCategoryRequestDto;
import com.miliancode.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface CategoryMapper {
    Category toModel(CreateCategoryRequestDto requestDto);

    CategoryDto toDto(Category category);

}
