package com.VoxelMaps.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getMainPage(ModelMap model, HttpServletRequest request)
    {
        if (request.getUserPrincipal() != null) {
            if (request.isUserInRole("ROLE_ADMIN")) {
                model.addAttribute("isAdmin", true);
            }
            else {
                model.addAttribute("isAdmin", false);
            }
            model.addAttribute("username", request.getUserPrincipal().getName());
        }
        else {
            model.addAttribute("username", null);
            model.addAttribute("isAdmin", false);
        }
        return "main";
    }
}