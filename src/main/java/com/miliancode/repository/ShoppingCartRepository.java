package com.miliancode.repository;

import com.miliancode.model.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("SELECT sc FROM ShoppingCart sc WHERE sc.user.id = :id")
    Optional<ShoppingCart> findShoppingCartByUserId(Long id);
}
