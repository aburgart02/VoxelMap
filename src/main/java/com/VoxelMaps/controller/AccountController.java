package com.VoxelMaps.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController
{
    @RequestMapping("/account")
    public String getMainPage(ModelMap model, HttpServletRequest request) {
        String user=request.getUserPrincipal().getName();
        model.addAttribute("username", user);
        return "account";
    }
}
