package LootlyBackend.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDto {

	private Long profileId;

    private UserDto user;
    private String contactNumber;
    
    private String profileImage;
    
}
