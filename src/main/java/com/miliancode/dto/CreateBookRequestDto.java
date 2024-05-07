package com.miliancode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String isbn;
    @Positive
    private BigDecimal price;
    @Size(min = 5, message = "Description cannot be that short")
    private String description;
    private String coverImage;
}
