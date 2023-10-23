package com.VoxelMaps.controller;

import java.io.IOException;
import com.VoxelMaps.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ImageUploadController {
    @Autowired
    private ImageService imageService;

    @RequestMapping("/upload-image")
    public ModelAndView showUpload() {
        return new ModelAndView("image-uploading");
    }

    @PostMapping("/upload-image")
    public ModelAndView fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

        String uploadImage = imageService.uploadImage(file);
        return new ModelAndView("image-uploading");
    }
}