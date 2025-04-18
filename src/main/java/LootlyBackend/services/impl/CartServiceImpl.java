package LootlyBackend.services.impl;

import LootlyBackend.entities.*;
import LootlyBackend.exceptions.ResourceNotFoundException;
import LootlyBackend.payloads.CartDto;
import LootlyBackend.payloads.CartItemDto;
import LootlyBackend.repository.CartRepo;
import LootlyBackend.repository.ProductRepo;
import LootlyBackend.repository.UserRepo;
import LootlyBackend.services.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ProductRepo productRepository;

    @Override
    public CartDto getCartByUserId(Integer userId) {
        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "userId", userId));
        return mapToDto(cart);
    }

    @Override
    public CartDto addItemToCart(Integer userId, Integer productId, Integer quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Cart cart = cartRepository.findByUser_Id(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return newCart;
        });

        CartItem item = cart.getItems().stream()
                .filter(ci -> ci.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProduct(product);
                    newItem.setQuantity(0);
                    return newItem;
                });

        item.setQuantity(quantity);
        item.setPrice(product.getBase_price());
        item.setDiscount(0);
        item.setTotalPrice(product.getBase_price() * quantity);

        // ✅ Set the product image name
        String imageName = product.getImageName();
        System.out.println("Setting product image: " + imageName); // Debug log
        item.setProductImage(imageName);
        if (!cart.getItems().contains(item)) {
            cart.getItems().add(item);
        }
        cart.getItems().forEach(i -> System.out.println("preoduct image"+i.getProductImage()));
        System.out.println("Setting carrt: " + cart.getItems());
        Cart savedCart = cartRepository.save(cart);
        return mapToDto(savedCart);
    }



    @Override
    public CartDto removeItemFromCart(Integer userId, Integer productId) {
        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "userId", userId));

        cart.getItems().removeIf(item -> item.getProduct().getProductId().equals(productId));
        cartRepository.save(cart);

        return mapToDto(cart);
    }

    @Override
    public CartDto updateItemQuantity(Integer userId, Integer productId, Integer quantity) {
        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "userId", userId));

        CartItem item = cart.getItems().stream()
                .filter(ci -> ci.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "productId", productId));

        item.setQuantity(quantity);
        cartRepository.save(cart);

        return mapToDto(cart);
    }

    @Override
    public void clearCart(Integer userId) {
        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "userId", userId));

        cart.getItems().clear();
        cartRepository.save(cart);
    }

    private CartDto mapToDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setCartId(cart.getCartId());
        dto.setUserId(cart.getUser().getId());

        dto.setItems(cart.getItems().stream().map(item -> {
            CartItemDto itemDto = new CartItemDto();
            itemDto.setCartItemId(item.getCartItemId());
            itemDto.setProductId(item.getProduct().getProductId());
            itemDto.setProductName(item.getProduct().getTitle());
            itemDto.setProductPrice(item.getProduct().getBase_price());
            itemDto.setQuantity(item.getProduct().getQuantity());
            itemDto.setStock(item.getProduct().getStock());
            itemDto.setUserQuantity(item.getQuantity());  
            itemDto.setProductImage(item.getProduct().getImageName());
            itemDto.setTotalPrice(item.getProduct().getBase_price() * item.getQuantity());
            return itemDto;
        }).collect(Collectors.toList()));

        dto.setTotalCartPrice((float)dto.getItems().stream()
                .mapToDouble(CartItemDto::getTotalPrice)
                .sum());

        return dto;
}
}
