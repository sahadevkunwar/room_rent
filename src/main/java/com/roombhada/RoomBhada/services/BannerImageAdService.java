package com.roombhada.RoomBhada.services;

import com.roombhada.RoomBhada.entities.BannerImageAd;

import java.util.List;

public interface BannerImageAdService {

    //create
    public BannerImageAd addBannerImageAds(BannerImageAd bannerImageAd);

    //get all banner images
    public List<BannerImageAd> getAllBannerAds();

}
