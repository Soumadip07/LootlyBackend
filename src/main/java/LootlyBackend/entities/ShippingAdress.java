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
@Table(name="shipping_address")
@Getter
@Setter
@NoArgsConstructor
public class ShippingAdress {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer shippingId;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "user_id", nullable = false, unique = true)  // One user, one shipping address
	 private User user;
	
	@Column(name="full_adress")
	private String fullAddress;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zip_code")
	private String zipCode;

}
