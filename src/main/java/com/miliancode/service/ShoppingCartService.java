package com.miliancode.service;

import com.miliancode.dto.cartitem.CreateCartItemRequestDto;
import com.miliancode.dto.cartitem.UpdateCartItemRequestDto;
import com.miliancode.dto.shoppingcart.ShoppingCartDto;
import com.miliancode.model.User;

public interface ShoppingCartService {
    ShoppingCartDto addItemToShoppingCart(CreateCartItemRequestDto createCartItemRequestDto,
                                          Long userId);

    ShoppingCartDto getShoppingCart(Long userId);

    ShoppingCartDto updateCartItem(Long itemId, UpdateCartItemRequestDto updateCartItemRequestDto,
                                   Long userId);

    void deleteCartItem(Long itemId, Long userId);

    void createShoppingCart(User user);
}
