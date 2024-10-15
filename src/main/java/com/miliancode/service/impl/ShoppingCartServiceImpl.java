package com.miliancode.service.impl;

import com.miliancode.dto.cartitem.CreateCartItemRequestDto;
import com.miliancode.dto.cartitem.UpdateCartItemRequestDto;
import com.miliancode.dto.shoppingcart.ShoppingCartDto;
import com.miliancode.exception.EntityNotFoundException;
import com.miliancode.mapper.CartItemMapper;
import com.miliancode.mapper.ShoppingCartMapper;
import com.miliancode.model.Book;
import com.miliancode.model.CartItem;
import com.miliancode.model.ShoppingCart;
import com.miliancode.model.User;
import com.miliancode.repository.BookRepository;
import com.miliancode.repository.CartItemRepository;
import com.miliancode.repository.ShoppingCartRepository;
import com.miliancode.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public ShoppingCartDto addItemToShoppingCart(CreateCartItemRequestDto createCartItemRequestDto,
                                                 Long userId) {
        ShoppingCart shoppingCart = getShoppingCartByUserId(userId);
        Book book = bookRepository.findById(createCartItemRequestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find book by id = " + createCartItemRequestDto.getBookId()));
        shoppingCart.getCartItems().stream()
                .filter(item -> item.getBook().getId().equals(createCartItemRequestDto.getBookId()))
                .findFirst()
                .ifPresentOrElse(item -> item.setQuantity(
                                item.getQuantity() + createCartItemRequestDto.getQuantity()),
                        () -> addCartItem(shoppingCart, book, createCartItemRequestDto));
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto getShoppingCart(Long userId) {
        ShoppingCart shoppingCart = getShoppingCartByUserId(userId);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCartDto updateCartItem(Long itemId,
                                          UpdateCartItemRequestDto updateCartItemRequestDto,
                                          Long userId) {
        CartItem cartItem = cartItemRepository
                .findByIdAndShoppingCartUserId(itemId, userId)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Can't find item with id" + itemId
                                        + "shopping cart for user with id "
                                        + userId
                        )
                );
        cartItem.setQuantity(updateCartItemRequestDto.getQuantity());
        return getShoppingCart(userId);
    }

    @Override
    @Transactional
    public void deleteCartItem(Long itemId, Long userId) {
        if (!cartItemRepository.existsByIdAndShoppingCartUserId(itemId, userId)) {
            throw new EntityNotFoundException("Can`t find item with id " + itemId
                    + ", because it doesn't exists for user with id " + userId);
        }
        cartItemRepository.deleteById(itemId);
    }

    @Override
    public ShoppingCartDto createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    private ShoppingCart getShoppingCartByUserId(Long id) {
        return shoppingCartRepository.findShoppingCartByUserId(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can`t find shopping cart by id = " + id));
    }

    private void addCartItem(
            ShoppingCart shoppingCart,
            Book book, CreateCartItemRequestDto cartItemRequestDto) {
        CartItem newCartItem = cartItemMapper.toEntity(cartItemRequestDto);
        newCartItem.setBook(book);
        shoppingCart.addItemToCart(newCartItem);
    }
}
