package LootlyBackend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LootlyBackend.entities.TopBanner;
import LootlyBackend.payloads.TopBannerDto;
import LootlyBackend.repository.TopBannerRepo;
import LootlyBackend.services.TopBannerService;


@Service
public class TopBannerServiceImpl implements TopBannerService{
	
	

	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private TopBannerRepo topBannerRepo;
	
	@Override
	public TopBannerDto createTopBanner(TopBannerDto topBannerDto) {
		TopBanner topBanner=this.modelMapper.map(topBannerDto, TopBanner.class);
		topBanner.setOffer_sale_text(topBanner.getOffer_sale_text());
		topBanner.setOffer_text(topBanner.getOffer_text());
		topBanner.setOffer_desc_text(topBanner.getOffer_desc_text());
		topBanner.setOffer_code_text(topBanner.getOffer_code_text());
		topBanner.setImageName("default.png");

		TopBanner newTopBanner=this.topBannerRepo.save(topBanner);
		
		return this.modelMapper.map(newTopBanner, TopBannerDto.class);
		}

	   @Override
	    public TopBannerDto getTopBannerById(Long id) {
	        TopBanner topBanner = topBannerRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Top Banner not found with id: " + id));
	        return this.modelMapper.map(topBanner, TopBannerDto.class);
	    }

	    @Override
	    public List<TopBannerDto> getAllTopBanners() {
	        List<TopBanner> banners = topBannerRepo.findAll();
	        return banners.stream()
	                .map(banner -> this.modelMapper.map(banner, TopBannerDto.class))
	                .collect(Collectors.toList());
	    }

	    @Override
	    public TopBannerDto updateTopBanner(Long id, TopBannerDto topBannerDto) {
	        TopBanner topBanner = topBannerRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Top Banner not found with id: " + id));

	        topBanner.setOffer_sale_text(topBannerDto.getOffer_sale_text());
	        topBanner.setOffer_text(topBannerDto.getOffer_text());
	        topBanner.setOffer_desc_text(topBannerDto.getOffer_desc_text());
	        topBanner.setOffer_code_text(topBannerDto.getOffer_code_text());
	        if (topBannerDto.getImageName() != null) {
	            topBanner.setImageName(topBannerDto.getImageName());
	        }

	        TopBanner updatedBanner = topBannerRepo.save(topBanner);
	        return this.modelMapper.map(updatedBanner, TopBannerDto.class);
	    }
	@Override
	public void deleteTopBanner(Long id) {
		// TODO Auto-generated method stub
		
	}

}
