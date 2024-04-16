package com.example.mapper;

import com.example.dto.BookDto;
import com.example.dto.CreateBookRequestDto;
import com.example.model.Book;

public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
