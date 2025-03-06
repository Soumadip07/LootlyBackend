package LootlyBackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
}
