package LootlyBackend.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LootlyBackend.entities.Profile;
import LootlyBackend.entities.User;
import LootlyBackend.exceptions.ResourceNotFoundException;
import LootlyBackend.payloads.ProfileDto;
import LootlyBackend.repository.ProfileRepo;
import LootlyBackend.repository.UserRepo;
import LootlyBackend.services.ProfileService;

@Service
public class ProfileServiceImpl  implements ProfileService{

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Create Profile
    @Override
    public ProfileDto createProfile(ProfileDto profileDto, Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow();

        Profile profile = modelMapper.map(profileDto, Profile.class);
        profile.setUser(user);
        
        Profile savedProfile = profileRepo.save(profile);
        return modelMapper.map(savedProfile, ProfileDto.class);
    }

	@Override
	public ProfileDto getProfileById(Long profileId) {
	      Profile profile = profileRepo.findById(profileId)
	                .orElseThrow(() -> new ResourceNotFoundException("Profile", "id", profileId));
	        return modelMapper.map(profile, ProfileDto.class);
	}
	@Override
	public ProfileDto getProfileByUserId(Integer userId) {
	
	    User user = userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	    
	    Profile profile = profileRepo.findByUserId(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("Profile", "userId", userId));
	    
	    return modelMapper.map(profile, ProfileDto.class);
	}

    @Override
    public ProfileDto updateProfile(ProfileDto profileDto, Long profileId) {
        Profile profile = profileRepo.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "id", profileId));

        profile.setProfileImage(profileDto.getProfileImage());

        profile.setContactNumber(profileDto.getContactNumber());

        Profile updatedProfile = profileRepo.save(profile);
        return modelMapper.map(updatedProfile, ProfileDto.class);
    }


}
