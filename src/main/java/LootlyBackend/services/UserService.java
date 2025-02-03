package LootlyBackend.services;

import java.util.List;

import LootlyBackend.entities.User;
import LootlyBackend.payloads.UserDto;

public interface UserService {
	
	
	UserDto registerNewUser(UserDto user);
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
}
