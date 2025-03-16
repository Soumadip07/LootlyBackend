package LootlyBackend.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LootlyBackend.entities.Profile;
import LootlyBackend.entities.User;
import LootlyBackend.payloads.ProfileDto;
import LootlyBackend.repository.ProfileRepo;
import LootlyBackend.repository.UserRepo;

@Service
public interface ProfileService {
	
	
	ProfileDto createProfile(ProfileDto profileDto, Integer userId);
	ProfileDto getProfileById(Long profileId);
	ProfileDto updateProfile(ProfileDto profileDto, Long profileId);
	 
}
