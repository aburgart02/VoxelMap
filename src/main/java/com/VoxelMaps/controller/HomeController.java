package com.VoxelMaps.controller;

import com.VoxelMaps.utils.AuthorizationValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getMainPage(ModelMap model, HttpServletRequest request)
    {
        AuthorizationValidator.validateAuthorization(model, request);
        return "main";
    }
}