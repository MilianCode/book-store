package com.miliancode.mapper;

import com.miliancode.config.MapStructConfig;
import com.miliancode.dto.book.BookDto;
import com.miliancode.dto.book.CreateBookRequestDto;
import com.miliancode.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
