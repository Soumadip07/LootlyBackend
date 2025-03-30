package LootlyBackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="top_banner")
@Getter
@Setter
@NoArgsConstructor
public class TopBanner {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 @Column(name = "top_banner_id")  
	 private Long top_banner_id; 
	 
	private String offer_sale_text;
		
	@Lob
	@Column(columnDefinition = "TEXT")
	private String offer_text;
	 
	private String offer_desc_text;
	
	private String offer_code_text;
	
	 private String imageUrl;
	 
	private String imageName;

}
