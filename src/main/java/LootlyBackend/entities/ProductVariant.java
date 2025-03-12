 package LootlyBackend.entities;

import java.util.Date;

import LootlyBackend.utils.DiscountType;
import LootlyBackend.utils.Quantity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
@Table(name="product_variant")
@Getter
@Setter
@NoArgsConstructor
public class ProductVariant {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer productVariantId;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "product_id", nullable = false)
	 private Product product;
	
	 @Column(name="color")
	 private String color;
	 
	 @Column(name="size")
	 private String size;
	 
	 @Column(name="price")
	 private Float price;
	 
	  @Column(name = "stock")
	  private Integer stock; 
	  
	  @Column(name = "base_price", nullable = true)
	    private Float basePrice;   // optional override

	    @Column(name = "discount", nullable = true)
	    private Float discount = 0.0f;   // optional override

	    @Enumerated(EnumType.STRING)
	    @Column(name = "discount_type", nullable = true)
	    private DiscountType discountType;  // optional override

	    @Enumerated(EnumType.STRING)
	    @Column(name = "quantity", nullable = true)
	    private Quantity quantity;   // optional override (pack size)

	    private String imageName;   // optional override for variant-specific image

	}

