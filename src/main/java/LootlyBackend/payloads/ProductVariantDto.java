package LootlyBackend.payloads;


import LootlyBackend.utils.DiscountType;
import LootlyBackend.utils.Quantity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductVariantDto {
	
	private Integer productVariantId;
    private String color;
    private String size;
    private Float price;
    private Integer stock;
    
    private Float basePrice;   
    private Float discount;    
    private DiscountType discountType;
    private Quantity quantity;
    private String imageName;
}
