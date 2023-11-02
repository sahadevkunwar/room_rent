package com.roombhada.RoomBhada.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roombhada.RoomBhada.entities.BannerImageAd;
import com.roombhada.RoomBhada.services.BannerImageAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/banner")
@CrossOrigin("*")
public class BannerImageAdController {

    @Autowired
    private BannerImageAdService bannerImageAdService;

    @Autowired
    private ServletContext servletContext;

    @Value("${project.image}")
    private String path;

   //create banner image ads
    @PostMapping("/")
    public ResponseEntity<?> createBannerAds(@RequestParam("file") MultipartFile file, @RequestParam("banner") String banner) throws JsonProcessingException, JsonMappingException, JsonParseException, IOException {

        BannerImageAd banner1 = new ObjectMapper().readValue(banner, BannerImageAd.class);
        banner1.setCreatedDate(new Date());
/*
        boolean isExist = new File(servletContext.getRealPath("/banner_image/")).exists();
        if(!isExist) {
            new File(servletContext.getRealPath("/banner_image")).mkdir();
            System.out.println("made folder");
        }
*/
        //create folder if not created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }


        String filename = file.getOriginalFilename();
        //random name generate file
        String randomID = UUID.randomUUID().toString();
        String modifiedFileName = randomID.concat(filename.substring(filename.lastIndexOf(".")));

        String serverFile = path +File.separator+modifiedFileName;
        try {
            Files.copy(file.getInputStream(), Paths.get(serverFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        banner1.setImageName(modifiedFileName);

        BannerImageAd bannerImage  =  this.bannerImageAdService.addBannerImageAds(banner1);

      return  ResponseEntity.ok(bannerImage);
    }

    //get all banner ads list
    @GetMapping("/")
    public ResponseEntity<?> getAllBannerAds() {
        return ResponseEntity.ok(this.bannerImageAdService.getAllBannerAds());
    }

    //get all banner image list
     @GetMapping(value = "/images")
     public void getAllBannerAds(HttpServletResponse response) throws IOException {
      List<String> images = new ArrayList<String>();
      String filesPath = path;
      File fileFolder = new File(filesPath);
      try {
          if (fileFolder != null) {
              for (final File file : fileFolder.listFiles()) {
                  if (!file.isDirectory()) {
                      InputStream resource = new FileInputStream(file);
                      StreamUtils.copy(resource, response.getOutputStream());
                  }
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
      }

     }

    //get all banner images by filename
    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getAllBannerImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        this.bannerImageAdService.getAllBannerAds();

        String fullPath = path+File.separator+imageName;

        InputStream resource = new FileInputStream(fullPath);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }


}
