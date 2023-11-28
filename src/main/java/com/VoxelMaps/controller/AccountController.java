package com.VoxelMaps.controller;

import com.VoxelMaps.model.Map;
import com.VoxelMaps.model.User;
import com.VoxelMaps.repository.MapRepository;
import com.VoxelMaps.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController
{
    @Autowired
    private UserService userService;

    @Autowired
    MapRepository mapRepository;

    @RequestMapping("/account")
    public String getMainPage(ModelMap model, HttpServletRequest request) {
        String username=request.getUserPrincipal().getName();
        User user = userService.loadUserByUsername(username);
        List<Map> favouriteMaps = new ArrayList<Map>();
        if (!user.favouriteMaps.equals("")) {
            String[] mapIds = user.favouriteMaps.split(":");
            for (var i = 0; i < mapIds.length; i++) {
                favouriteMaps.add(mapRepository.findById((long) Integer.parseInt(mapIds[i])).get());
            }
        }
        model.addAttribute("username", username);
        model.addAttribute("maps", user.getMaps());
        model.addAttribute("favouriteMaps", favouriteMaps);
        return "account";
    }
}
