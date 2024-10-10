package com.miliancode.repository;

import com.miliancode.model.CartItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByIdAndShoppingCartUserId(Long itemId, Long userId);

    boolean existsByIdAndShoppingCartUserId(Long itemId, Long userId);
}
