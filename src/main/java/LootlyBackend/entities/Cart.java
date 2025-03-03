package LootlyBackend.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import LootlyBackend.utils.DiscountType;
import LootlyBackend.utils.Quantity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cart")
@Getter
@Setter
@NoArgsConstructor
public class Cart {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer cartId;

	    @OneToOne
	    private User user;  // Each user has one cart.

	    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<CartItem> items = new HashSet<>();  // Set of items in the cart.

	    @Column(name = "total_price")
	    private float totalPrice;   // Optional - can also be computed dynamically.

	    @Temporal(TemporalType.TIMESTAMP)
	    private Date createdDate;

	    @Temporal(TemporalType.TIMESTAMP)
	    private Date updatedDate;
}
