package com.VoxelMaps.controller;

import com.VoxelMaps.model.Map;
import com.VoxelMaps.service.ImageService;
import com.VoxelMaps.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

@Controller
public class MapUploadingController {
    @Autowired
    private MapService mapService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/upload-map")
    public String registration(Model model) {
        model.addAttribute("map", new Map());
        return "map-uploading";
    }

    @PostMapping("/upload-map")
    public String addUser(@ModelAttribute("map") Map map, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Error");
        }
        mapService.saveMap(map);
        return "map-uploading";
    }

    @PostMapping("/upload-image")
    public ModelAndView fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadImage = imageService.uploadImage(file);
        return new ModelAndView("map-uploading");
    }
}