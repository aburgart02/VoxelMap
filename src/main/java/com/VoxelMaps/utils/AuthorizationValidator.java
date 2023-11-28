package com.VoxelMaps.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;

public class AuthorizationValidator {

    public static void validateAuthorization(ModelMap model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_USER")) {
            model.addAttribute("username", request.getUserPrincipal().getName());
            model.addAttribute("isAdmin", false);
        }
        else if (request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("username", request.getUserPrincipal().getName());
            model.addAttribute("isAdmin", true);
        }
        else {
            model.addAttribute("username", null);
            model.addAttribute("isAdmin", false);
        }
    }
}