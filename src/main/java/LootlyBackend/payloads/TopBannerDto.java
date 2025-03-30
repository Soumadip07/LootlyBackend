package LootlyBackend.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopBannerDto {
	
	private Long top_banner_id;
	
	private String offer_sale_text;
	
	private String offer_text;
	
	private String offer_desc_text;
	
	private String offer_code_text;
	 private String imageUrl;
    private String imageName;
}
