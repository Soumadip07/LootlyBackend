package LootlyBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import LootlyBackend.payloads.TopBannerDto;

@Service
public interface TopBannerService {

	TopBannerDto createTopBanner(TopBannerDto topBannerDto);

	TopBannerDto getTopBannerById(Long id);
    List<TopBannerDto> getAllTopBanners();
    TopBannerDto updateTopBanner(Long id, TopBannerDto topBannerDto);
    void deleteTopBanner(Long id);

}
