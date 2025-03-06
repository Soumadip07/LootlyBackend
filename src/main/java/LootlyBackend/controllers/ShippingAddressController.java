package LootlyBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LootlyBackend.payloads.ShippingAddressDto;
import LootlyBackend.services.ShippingAddressService;

@RestController
@RequestMapping("/api/shipping-address")
public class ShippingAddressController {
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	
	@PostMapping("/user/{userId}")
	public ResponseEntity<ShippingAddressDto> createShippingAddress(
			@PathVariable Integer userId,
			@RequestBody ShippingAddressDto shippingAddressDto
			)
	{
		ShippingAddressDto newshippingAddress=this.shippingAddressService.createShippingAddress(userId, shippingAddressDto);
		return new ResponseEntity<ShippingAddressDto>(newshippingAddress,HttpStatus.CREATED);
		
	}
	 // Update Shipping Address
    @PutMapping("/user/{userId}")
    public ResponseEntity<ShippingAddressDto> updateShippingAddress(
            @PathVariable Integer userId,
            @RequestBody ShippingAddressDto shippingAddressDto) {

        ShippingAddressDto updatedAddress = shippingAddressService.updateShippingAddress(userId, shippingAddressDto);
        return ResponseEntity.ok(updatedAddress);
    }

    // Get Shipping Address by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<ShippingAddressDto> getShippingAddressByUserId(@PathVariable Integer userId) {
        ShippingAddressDto shippingAddress = shippingAddressService.getShippingAddressByUserId(userId);
        return ResponseEntity.ok(shippingAddress);
    }

    // Delete Shipping Address by Shipping Address ID
    @DeleteMapping("/{shippingId}")
    public ResponseEntity<String> deleteShippingAddress(@PathVariable Integer shippingId) {
        shippingAddressService.deleteShippingAddress(shippingId);
        return ResponseEntity.ok("Shipping address deleted successfully.");
    }
}
	