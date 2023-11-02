package com.roombhada.RoomBhada.services.impl;

import com.roombhada.RoomBhada.entities.BannerImageAd;
import com.roombhada.RoomBhada.repositories.BannerImageAdRepository;
import com.roombhada.RoomBhada.services.BannerImageAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerImageAdServiceImpl implements BannerImageAdService {

    @Autowired
    private BannerImageAdRepository bannerImageAdRepository;

    //add banner images
    @Override
    public BannerImageAd addBannerImageAds(BannerImageAd bannerImageAd) {
        return this.bannerImageAdRepository.save(bannerImageAd);
    }

    //get all banner images
    @Override
    public List<BannerImageAd> getAllBannerAds() {
        return this.bannerImageAdRepository.findAll();
    }

}
