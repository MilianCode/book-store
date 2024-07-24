package com.miliancode.mapper;

import com.miliancode.config.MapStructConfig;
import com.miliancode.dto.book.BookDto;
import com.miliancode.dto.book.BookDtoWithoutCategotyIds;
import com.miliancode.dto.book.CreateBookRequestDto;
import com.miliancode.model.Book;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    BookDtoWithoutCategotyIds toDtoWithoutCategoryIds(Book book);

    @AfterMapping
    default void setCategoriesIds(@MappingTarget BookDto bookDto, Book book) {
        bookDto.setCategories(book.getCategories());
    }
}
