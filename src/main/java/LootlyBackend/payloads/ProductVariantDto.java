package LootlyBackend.payloads;


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
    
    
}
