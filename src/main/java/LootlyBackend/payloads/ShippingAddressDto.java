package LootlyBackend.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShippingAddressDto {

	 private Integer shippingId;  
	 private Integer userId;      
	 private String fullAddress;
	 private String state;
	 private String city;          
	 private String zipCode;
}
