package LootlyBackend.services;

import org.springframework.stereotype.Service;

import LootlyBackend.payloads.ShippingAddressDto;

@Service
public interface ShippingAddressService {
	  ShippingAddressDto createShippingAddress(Integer userId, ShippingAddressDto shippingAddressDto);

	    ShippingAddressDto updateShippingAddress(Integer userId, ShippingAddressDto shippingAddressDto);

	    ShippingAddressDto getShippingAddressByUserId(Integer userId);

	    void deleteShippingAddress(Integer shippingId);
}
