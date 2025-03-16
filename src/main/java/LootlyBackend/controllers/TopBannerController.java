package LootlyBackend.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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

import LootlyBackend.payloads.TopBannerDto;
import LootlyBackend.services.FileService;
import LootlyBackend.services.TopBannerService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class TopBannerController {
	
	@Autowired
	private FileService fileService;
	
	
	@Value("${project.image}")
	private String path;
	
	@Autowired
	private TopBannerService topBannerService;	
	
	
	
	//create
	@PostMapping("/top-banner")
	public ResponseEntity<TopBannerDto> createTopBanner(
			@RequestBody TopBannerDto topBannerDto)
	{
		TopBannerDto createTopBanner=this.topBannerService.createTopBanner(topBannerDto);
		return new ResponseEntity<TopBannerDto>(createTopBanner,HttpStatus.CREATED);
	}
	
	@GetMapping("/top-banner")
    public ResponseEntity<List<TopBannerDto>> getAllTopBanners() {
        List<TopBannerDto> allBanners = this.topBannerService.getAllTopBanners();
        return new ResponseEntity<>(allBanners, HttpStatus.OK);
    }
	//top Banner image upload
	@PostMapping("/top-banner/{top_banner_id}")
			public ResponseEntity<TopBannerDto> uploadTopBannerImage(
				@RequestParam("image") MultipartFile image,
				@PathVariable Long top_banner_id
				) throws IOException{
			TopBannerDto topBannerDto=this.topBannerService.getTopBannerById(top_banner_id);
			
			String fileName=this.fileService.uploadImage(path, image);
			topBannerDto.setImageName(fileName);
			TopBannerDto updatedTopBanner=this.topBannerService.updateTopBanner(top_banner_id,topBannerDto);
			return new ResponseEntity<TopBannerDto>(updatedTopBanner, HttpStatus.OK);
		}
		
		
		 //method to serve files
		@GetMapping(value = "/top-banner/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	    public void downloadImage(
	            @PathVariable("imageName") String imageName,
	            HttpServletResponse response
	    ) throws IOException {

	        InputStream resource = this.fileService.getResource(path, imageName);
	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        StreamUtils.copy(resource,response.getOutputStream())   ;

	    }
}
