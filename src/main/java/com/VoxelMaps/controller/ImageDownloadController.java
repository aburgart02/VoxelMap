package com.VoxelMaps.controller;
import com.VoxelMaps.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/download-image")
public class ImageDownloadController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/{imageId}")
    public ResponseEntity<?> downloadImage(@PathVariable String imageId) {
        byte[] imageData = imageService.downloadImage(Integer.parseInt(imageId));
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(imageData);
    }
}