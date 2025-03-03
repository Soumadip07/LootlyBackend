package LootlyBackend.payloads;

import java.util.Date;
import java.util.Set;

import LootlyBackend.utils.DiscountType;
import LootlyBackend.utils.Quantity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDto {
	  private Integer cartItemId;

	    private Integer productId;

	    private String productName;

	    private float productPrice;

	    private Quantity quantity;

	    private Integer userQuantity;
	    private float totalPrice;  // productPrice * itemCount
}
