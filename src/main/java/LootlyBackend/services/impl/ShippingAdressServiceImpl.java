package LootlyBackend.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LootlyBackend.entities.ShippingAdress;
import LootlyBackend.entities.User;
import LootlyBackend.exceptions.ResourceNotFoundException;
import LootlyBackend.payloads.ShippingAddressDto;
import LootlyBackend.repository.ShippingAddressRepo;
import LootlyBackend.repository.UserRepo;
import LootlyBackend.services.ShippingAddressService;

@Service
public class ShippingAdressServiceImpl implements ShippingAddressService{
	
	
	@Autowired
	private ShippingAddressRepo shippingAddressRepo;
	
	@Autowired
    private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ShippingAddressDto createShippingAddress(Integer userId, ShippingAddressDto shippingAddressDto) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		ShippingAdress shippingAddress=this.modelMapper.map(shippingAddressDto, ShippingAdress.class);
		shippingAddress.setUser(user);
		shippingAddress.setFullAddress(shippingAddressDto.getFullAddress());
	    shippingAddress.setState(shippingAddressDto.getState());
	    shippingAddress.setCity(shippingAddressDto.getCity());
	    shippingAddress.setZipCode(shippingAddressDto.getZipCode());
	    ShippingAdress newShippingAdress=this.shippingAddressRepo.save(shippingAddress);
		return this.modelMapper.map(newShippingAdress, ShippingAddressDto.class);
	}

	@Override
	public ShippingAddressDto updateShippingAddress(Integer userId, ShippingAddressDto shippingAddressDto) {
		ShippingAdress shippingAdress=this.shippingAddressRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Shipping Address","user id",userId));
		shippingAdress.setFullAddress(shippingAddressDto.getFullAddress());
		shippingAdress.setState(shippingAddressDto.getState());
		shippingAdress.setCity(shippingAddressDto.getCity());
		shippingAdress.setZipCode(shippingAddressDto.getZipCode());
		
		
		ShippingAdress updatedShippingAdress=this.shippingAddressRepo.save(shippingAdress);
		
		return this.modelMapper.map(updatedShippingAdress, ShippingAddressDto.class);
	}

	@Override
	public ShippingAddressDto getShippingAddressByUserId(Integer userId) {
		
		  ShippingAdress shippingAddress = shippingAddressRepo.findByUser_Id(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("Shipping Address", "userId", userId));
		return this.modelMapper.map(shippingAddress, ShippingAddressDto.class);
	}

	@Override
	public void deleteShippingAddress(Integer shippingId) {
		ShippingAdress shippingAdress=this.shippingAddressRepo.findById(shippingId).orElseThrow(()-> new ResourceNotFoundException("Shipping Address","user id",shippingId));
		this.shippingAddressRepo.delete(shippingAdress);
	}

		
}
