package LootlyBackend.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import LootlyBackend.payloads.ProfileDto;
import LootlyBackend.services.FileService;
import LootlyBackend.services.ProfileService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
	  @Autowired
	    private ProfileService profileService;
	  
	   @Autowired
	    private FileService fileService;

	   @Value("${project.image}")
		private String path;
	   


	    // Create Profile
	    @PostMapping("/user/{userId}")
	    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto, @PathVariable Integer userId) {
	        ProfileDto createdProfile = profileService.createProfile(profileDto, userId);
	        return ResponseEntity.ok(createdProfile);
	    }
	 // Get Profile by User ID
	    @GetMapping("/user/{userId}")
	    public ResponseEntity<ProfileDto> getProfileByUserId(@PathVariable Integer userId) {
	        ProfileDto profileDto = profileService.getProfileByUserId(userId);
	        return ResponseEntity.ok(profileDto);
	    }

	    @PostMapping("/image/upload/{profileId}")
	    public ResponseEntity<ProfileDto> uploadProfileImage(
	    		@PathVariable Long profileId,
	            @RequestParam("image") MultipartFile image
	            ) throws IOException {


	        ProfileDto profileDto = this.profileService.getProfileById(profileId);

	        String fileName = this.fileService.uploadImage(path, image);

	        profileDto.setProfileImage(fileName);

	        ProfileDto updatedProfile = this.profileService.updateProfile(profileDto, profileId);

	        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
	    }

	    
	    // Serve Profile Image
	    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	    public void downloadProfileImage(
	    		@PathVariable("imageName") String imageName,
	    		HttpServletResponse response
	    		) throws IOException {
	    	InputStream resource = this.fileService.getResource(path, imageName);
	    	response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    	StreamUtils.copy(resource, response.getOutputStream());
	    }
}
