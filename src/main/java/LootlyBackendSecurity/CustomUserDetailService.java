package LootlyBackendSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import LootlyBackend.entities.User;
import LootlyBackend.exceptions.ResourceNotFoundException;
import LootlyBackend.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		//loading user from db by username
		User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email :"+username,0));
	
		return user;
	}
	
	
}
