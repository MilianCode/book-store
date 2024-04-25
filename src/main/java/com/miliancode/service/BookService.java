package com.miliancode.service;

import com.miliancode.dto.BookDto;
import com.miliancode.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);
}
