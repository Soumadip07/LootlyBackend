package LootlyBackend.payloads;


import java.util.HashSet;
import java.util.Set;

import LootlyBackend.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4, message="Name should be minimum of 4 characters")
	private String name;
	
	@Email(message ="Email address is not Valid!!")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message="Password must be 6-10 characters")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles=new HashSet<>();

}
