package LootlyBackend.payloads;

import java.util.List;

import LootlyBackend.utils.Quantity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class CartDto {

	 private Integer cartId;

	    private Integer userId;  // Linking cart to user

	    private List<CartItemDto> items;

	    private float totalCartPrice;  // Sum of all item total prices
}
