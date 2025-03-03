package LootlyBackend.controllers;

import LootlyBackend.payloads.CartDto;
import LootlyBackend.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Get Cart by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable Integer userId) {
        CartDto cartDto = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    // Add Item to Cart
    @PostMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable Integer userId, 
                                                 @PathVariable Integer productId, 
                                                 @RequestParam Integer quantity) {
        CartDto updatedCart = cartService.addItemToCart(userId, productId, quantity);
        return new ResponseEntity<>(updatedCart, HttpStatus.CREATED);
    }

    // Remove Item from Cart
    @DeleteMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<CartDto> removeItemFromCart(@PathVariable Integer userId, 
                                                      @PathVariable Integer productId) {
        CartDto updatedCart = cartService.removeItemFromCart(userId, productId);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    // Update Item Quantity in Cart
    @PutMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<CartDto> updateItemQuantity(@PathVariable Integer userId, 
                                                      @PathVariable Integer productId, 
                                                      @RequestParam Integer quantity) {
        CartDto updatedCart = cartService.updateItemQuantity(userId, productId, quantity);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    // Clear Cart
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Integer userId) {
        cartService.clearCart(userId);
        return new ResponseEntity<>("Cart cleared successfully", HttpStatus.OK);
    }
}
