package com.VoxelMaps.controller;

import com.VoxelMaps.model.User;
import com.VoxelMaps.repository.UserRepository;
import com.VoxelMaps.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
public class UserActionController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;


    @RequestMapping(value="/add-to-favourites", method=RequestMethod.GET)
    private void addToFavourite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username=request.getUserPrincipal().getName();
        User user = userService.loadUserByUsername(username);
        boolean hasMap = false;
        String mapId = request.getParameter("mapId");
        String[] mapIds = user.favouriteMaps.split(":");
        for (var i = 0; i < mapIds.length; i++) {
            if (mapIds[i].equals(mapId)) {
                hasMap = true;
            }
        }
        if (!hasMap) {
            user.favouriteMaps += request.getParameter("mapId") + ":";
            userRepository.save(user);
        }
        response.sendRedirect("maps/" + request.getParameter("mapId"));
    }

    @RequestMapping(value="/remove-from-favourites", method=RequestMethod.GET)
    private void removeFromFavourite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username=request.getUserPrincipal().getName();
        User user = userService.loadUserByUsername(username);
        user.favouriteMaps = user.favouriteMaps.replaceAll(request.getParameter("mapId") + ":", "");
        userRepository.save(user);
        response.sendRedirect("account");
    }
}