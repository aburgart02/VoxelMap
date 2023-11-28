package com.VoxelMaps.controller;

import com.VoxelMaps.model.User;
import com.VoxelMaps.service.UserService;
import com.VoxelMaps.utils.AuthorizationValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users/{username}")
    public String getUserByUsername(ModelMap model, @PathVariable("username") String username, HttpServletRequest request)
    {
        if (request.getUserPrincipal() != null) {
            String localUsername = request.getUserPrincipal().getName();
            if (localUsername.equals(username))
                return "redirect:/account";
        }

        User user;
        try {
            user = userService.loadUserByUsername(username);
        } catch (Exception e) {
            return "not-found";
        }

        AuthorizationValidator.validateAuthorization(model, request);
        model.addAttribute("author", username);
        model.addAttribute("maps", user.getMaps());
        return "user";
    }
}