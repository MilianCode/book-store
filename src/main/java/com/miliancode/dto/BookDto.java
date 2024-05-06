package com.miliancode.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class BookDto {
    @NotBlank
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String isbn;
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
