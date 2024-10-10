package com.miliancode.controller;

import com.miliancode.dto.cartitem.CreateCartItemRequestDto;
import com.miliancode.dto.cartitem.UpdateCartItemRequestDto;
import com.miliancode.dto.shoppingcart.ShoppingCartDto;
import com.miliancode.model.User;
import com.miliancode.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cart")
@Tag(name = "Shopping cart management", description = "Endpoints to managing shopping carts")
@Validated
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get shopping car for authenticated user",
            description = "Returns list of CartItems from users shopping cart")
    public ShoppingCartDto getUsersShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getShoppingCart(user.getId());
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Adding item to shopping cart",
            description = "Adding item to users cart")
    public ShoppingCartDto addCartItemToShoppingCart(
            @RequestBody @Valid CreateCartItemRequestDto createCartItemRequestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addItemToShoppingCart(createCartItemRequestDto, user.getId());
    }

    @PutMapping("/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Updating cart item quantity", description = "Updating cart item quantity")
    public ShoppingCartDto updateItemQuantity(
            @PathVariable Long itemId,
            @RequestBody@Valid UpdateCartItemRequestDto updateCartItemRequestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateCartItem(itemId, updateCartItemRequestDto, user.getId());
    }

    @DeleteMapping("/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Deleting item from shopping cart",
            description = "Deleting item from users cart")
    public void deteleCartItem(@PathVariable Long itemId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        shoppingCartService.deleteCartItem(itemId, user.getId());
    }
}
