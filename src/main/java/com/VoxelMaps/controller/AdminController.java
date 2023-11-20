package com.VoxelMaps.controller;

import com.VoxelMaps.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/delete-user")
    public String deleteUser(HttpServletRequest request) {
        var userId = request.getParameter("id");
        userService.deleteUser(Long.parseLong(userId));
        return "redirect:/admin";
    }

}