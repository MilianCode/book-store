package com.miliancode.service.impl;

import com.miliancode.dto.book.BookDto;
import com.miliancode.dto.book.BookDtoWithoutCategotyIds;
import com.miliancode.dto.book.CreateBookRequestDto;
import com.miliancode.exception.EntityNotFoundException;
import com.miliancode.mapper.BookMapper;
import com.miliancode.model.Book;
import com.miliancode.model.Category;
import com.miliancode.repository.BookRepository;
import com.miliancode.repository.CategoryRepository;
import com.miliancode.service.BookService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private CategoryRepository categoryRepository;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        book.setCategories(mapCategoriesFromDto(requestDto));
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public List<BookDtoWithoutCategotyIds> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDtoWithoutCategoryIds)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cant find book by id = " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        book.setId(id);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDtoWithoutCategotyIds> findAllByCategoryId(Long id) {
        return bookRepository.findAllByCategoryId(id).stream()
                .map(bookMapper::toDtoWithoutCategoryIds)
                .toList();
    }

    private Set<Category> mapCategoriesFromDto(CreateBookRequestDto requestDto) {
        return requestDto.getCategoryIds().stream()
                .map(categoryId -> categoryRepository.findById(categoryId).orElseThrow(
                        () -> new EntityNotFoundException(
                                "Can't find category by id: " + categoryId)))
                .collect(Collectors.toSet());
    }
}
