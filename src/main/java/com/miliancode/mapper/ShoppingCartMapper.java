package com.miliancode.mapper;

import com.miliancode.config.MapStructConfig;
import com.miliancode.dto.shoppingcart.ShoppingCartDto;
import com.miliancode.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    @Mapping(source = "user.id", target = "userId")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);
}
