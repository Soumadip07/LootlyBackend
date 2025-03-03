package LootlyBackend.entities;

import java.util.Date;
import java.util.Set;

import LootlyBackend.utils.DiscountType;
import LootlyBackend.utils.Quantity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cart_item")
@Getter
@Setter
@NoArgsConstructor
public class CartItem {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer cartItemId; 
	 
	 @ManyToOne
	 @JoinColumn(name = "cart_id")
	 private Cart cart;  // Each item belongs to one cart.

	 @ManyToOne
	 @JoinColumn(name = "product_id")
	 private Product product;  
	 
	 @Column(name = "quantity")
	  private Integer quantity;

	 @Column(name = "price")
	  private float price;  
	 
	 @Column(name = "discount")
	 private float discount;  

	 @Column(name = "total_item_price")
	 private float totalItemPrice; 
	 
	
	 
}
