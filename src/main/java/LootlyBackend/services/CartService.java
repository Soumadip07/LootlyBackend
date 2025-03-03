package LootlyBackend.services;

import org.springframework.stereotype.Service;

import LootlyBackend.payloads.CartDto;
@Service

public interface CartService {

    CartDto getCartByUserId(Integer userId);

    CartDto addItemToCart(Integer userId, Integer productId, Integer quantity);

    CartDto removeItemFromCart(Integer userId, Integer productId);

    CartDto updateItemQuantity(Integer userId, Integer productId, Integer quantity);

    void clearCart(Integer userId);
}
