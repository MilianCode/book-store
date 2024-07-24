package com.miliancode.controller;

import com.miliancode.dto.book.BookDtoWithoutCategotyIds;
import com.miliancode.dto.category.CategoryDto;
import com.miliancode.dto.category.CreateCategoryRequestDto;
import com.miliancode.service.BookService;
import com.miliancode.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
@Tag(name = "Categories management", description = "Endpoints to managing categories")
@Validated
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PostMapping
    @Operation(summary = "Create a new category", description = "Create a new category")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto save(@RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @GetMapping
    @Operation(summary = "Get all categories", description = "Get a list of all categories")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CategoryDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category", description = "Delete category by id")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update category", description = "Update category by id")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto update(@RequestBody @Valid CreateCategoryRequestDto requestDto,
                              @PathVariable Long id) {
        return categoryService.update(id, requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category", description = "Get category by id")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public CategoryDto getById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/{id}/books")
    @Operation(summary = "Get books with category",
            description = "Get books by specific category id")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<BookDtoWithoutCategotyIds> getBooksByCategoryId(@PathVariable Long id) {
        return bookService.findAllByCategoryId(id);
    }
}
