package com.VoxelMaps.controller;

import com.VoxelMaps.repository.MapRepository;
import com.VoxelMaps.model.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MapsController {
    @Autowired
    MapRepository mapRepository;

    @RequestMapping("/maps")
    public String getMaps(ModelMap model) {
        List<Map> maps = new ArrayList<Map>(mapRepository.findAll());
        model.addAttribute("maps", maps);
        return "maps";
    }

    @RequestMapping("/maps/{id}")
    public String getMapById(ModelMap model, @PathVariable("id") long id)
    {
        Map map = mapRepository.findAll().get((int) id);
        model.addAttribute("map", map);
        return "map";
    }
}