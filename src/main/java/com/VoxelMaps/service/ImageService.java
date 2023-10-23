package com.VoxelMaps.service;
import com.VoxelMaps.utils.ImageUtils;
import com.VoxelMaps.model.Image;
import com.VoxelMaps.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String uploadImage(MultipartFile imageFile) throws IOException {
        var imageToSave = new Image(imageFile.getOriginalFilename(), imageFile.getContentType(),
                ImageUtils.compressImage(imageFile.getBytes()));
        System.out.println(imageToSave.name);
        imageRepository.save(imageToSave);
        return "file uploaded: " + imageFile.getOriginalFilename();
    }

    public byte[] downloadImage(String imageName) {
        Optional<Image> dbImage = imageRepository.findById(1L);
        return dbImage.map(image -> {
            try {
                return ImageUtils.decompressImage(image.getImageData());
            } catch (DataFormatException | IOException exception) {
                System.out.println("error");
            }
            return null;
        }).orElse(null);
    }
}