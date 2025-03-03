package LootlyBackend.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import LootlyBackend.utils.DiscountType;
import LootlyBackend.utils.Quantity;
import LootlyBackend.utils.StockEnum.StockStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer productId;
	
	
	@Column(name="post_title", length =100, nullable=false)
	private String title;
	
	@Lob
	@Column(columnDefinition = "TEXT")
	private String content;

	 
    @Column(name = "product_slug")
    private String productSlug;
    
    @Column(name = "base_price", nullable = true)  // nullable = true allows null values
    private Float base_price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "quantity")
    private Quantity quantity;
    
    @Column(name = "stock")
    private Integer stock; 
    
    @Column (name="discount")
    private float discount;
    
    @Enumerated(EnumType.STRING)
    @Column (name ="discount_type")
    private DiscountType DiscountType ;
    
	private String imageName;
	
	
	private Date addedDate;
	
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL)
	private Set<Review> reviews=new HashSet<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();
}
