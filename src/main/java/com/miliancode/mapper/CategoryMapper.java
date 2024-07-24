package com.miliancode.mapper;

import com.miliancode.config.MapStructConfig;
import com.miliancode.dto.book.BookDto;
import com.miliancode.dto.book.BookDtoWithoutCategotyIds;
import com.miliancode.dto.category.CategoryDto;
import com.miliancode.dto.category.CreateCategoryRequestDto;
import com.miliancode.model.Book;
import com.miliancode.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface CategoryMapper {
    Category toModel(CreateCategoryRequestDto requestDto);

    CategoryDto toDto(Category category);

}
