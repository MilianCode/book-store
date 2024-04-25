package com.miliancode.mapper;

import com.miliancode.config.MapStructConfig;
import com.miliancode.dto.BookDto;
import com.miliancode.dto.CreateBookRequestDto;
import com.miliancode.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
