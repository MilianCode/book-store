package com.miliancode.mapper;

import com.miliancode.config.MapStructConfig;
import com.miliancode.dto.cartitem.CartItemDto;
import com.miliancode.dto.cartitem.CreateCartItemRequestDto;
import com.miliancode.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface CartItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemDto toDto(CartItem cartItem);

    CartItem toEntity(CreateCartItemRequestDto cartItemRequestDto);
}
