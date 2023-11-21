package com.VoxelMaps.controller;

import com.VoxelMaps.model.Map;
import com.VoxelMaps.model.User;
import com.VoxelMaps.service.ImageService;
import com.VoxelMaps.service.MapFileService;
import com.VoxelMaps.service.MapService;
import com.VoxelMaps.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;

@Controller
public class MapUploadingController {
    @Autowired
    private MapService mapService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private MapFileService mapFileService;

    @Autowired
    private UserService userService;

    @GetMapping("/upload-map")
    public String registration(Model model, HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            model.addAttribute("username", request.getUserPrincipal().getName());
        }
        else {
            model.addAttribute("username", null);
        }
        model.addAttribute("map", new Map());
        return "map-uploading";
    }

//    @RequestMapping(value = "/upload-map", headers = "content-type=multipart/*", method = RequestMethod.POST)
//    public String addUser(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
//                          @RequestParam("size") int size, @RequestParam("weatherEffects") int[] weatherEffects,
//                          @RequestParam("timeOfDay") int timeOfDay, @RequestParam("gameMode") int gameMode,
//                          @RequestParam("description") String description) throws IOException {
//        System.out.println(1);
//        String uploadImage = imageService.uploadImage(file);
//        Map map = new Map();
//        map.setTitle(title);
//        map.setSize(size);
//        map.setWeatherEffects(weatherEffects);
//        map.setTimeOfDay(timeOfDay);
//        map.setGameMode(gameMode);
//        map.setDescription(description);
//        mapService.saveMap(map);
//        return "map-uploading";
//    }

    @RequestMapping(value={"/upload-map"}, method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    @ResponseBody
    public String upload(MultipartHttpServletRequest request, HttpServletResponse response,
                         @RequestParam("title") String title,
                         @RequestParam("size") int size, @RequestParam("weatherEffects") Optional<int[]> weatherEffects,
                         @RequestParam("timeOfDay") int timeOfDay, @RequestParam("gameMode") int gameMode,
                         @RequestParam("description") String description) throws IOException {
        String responseMessage = "OK";
        MultipartFile imageFile = request.getFile("imageFile");
        MultipartFile mapFile = request.getFile("mapFile");
        String uploadImageFile = imageService.uploadImage(imageFile);
        String uploadMapFile = mapFileService.uploadMapFile(mapFile);
        String userName = request.getUserPrincipal().getName();
        User user = userService.loadUserByUsername(userName);
        Map map = new Map();
        map.setTitle(title);
        map.setSize(size);
        map.setWeatherEffects(weatherEffects.orElse(null));
        map.setTimeOfDay(timeOfDay);
        map.setGameMode(gameMode);
        map.setDescription(description);
        map.setRating(0);
        map.setDateOfAddition(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        user.addMap(map);
        userService.saveUser(user);
        mapService.saveMap(map);
        response.sendRedirect("upload-map");
        return responseMessage;
    }
}