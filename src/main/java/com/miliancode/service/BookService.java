package com.miliancode.service;

import com.miliancode.dto.book.BookDto;
import com.miliancode.dto.book.BookDtoWithoutCategotyIds;
import com.miliancode.dto.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    void deleteById(Long id);

    BookDto update(Long id, CreateBookRequestDto requestDto);

    List<BookDtoWithoutCategotyIds> findAllByCategoryId(Long id);
}
