package com.VoxelMaps.controller;

import com.VoxelMaps.model.Map;
import com.VoxelMaps.repository.MapRepository;
import com.VoxelMaps.service.MapService;
import com.VoxelMaps.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    MapRepository mapRepository;

    @GetMapping("/admin")
    public String userList(Model model, HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            model.addAttribute("username", request.getUserPrincipal().getName());
        }
        else {
            model.addAttribute("username", null);
        }
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/delete-user")
    public String deleteUser(HttpServletRequest request) {
        var userId = request.getParameter("id");
        List<Map> maps = new ArrayList<Map>(mapRepository.findAll());
        for (int i = 0; i < maps.size(); i++) {
            var map = maps.get(i);
            if (map.getAuthor() != null) {
                if (map.getAuthor().getUserId() == Long.parseLong(userId)) {
                    map.setAuthor(null);
                    mapRepository.save(map);
                }
            }
        }
        userService.deleteUser(Long.parseLong(userId));
        return "redirect:/admin";
    }
}