package com.VoxelMaps.controller;

import com.VoxelMaps.model.ViewMap;
import com.VoxelMaps.repository.MapRepository;
import com.VoxelMaps.model.Map;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class MapsController {
    Dictionary<Integer, String> size = new Hashtable<>();
    Dictionary<Integer, String> weatherEffects = new Hashtable<>();
    Dictionary<Integer, String> timeOfDay = new Hashtable<>();
    Dictionary<Integer, String> gameMode = new Hashtable<>();

    @Autowired
    MapRepository mapRepository;

    public MapsController() {
        size.put(0, "128x128");
        size.put(1, "256x256");
        size.put(2, "512x512");

        weatherEffects.put(0, "Дождь");
        weatherEffects.put(1, "Туман");
        weatherEffects.put(2, "Песчаная буря");

        timeOfDay.put(0, "Утро");
        timeOfDay.put(0, "День");
        timeOfDay.put(0, "Вечер");
        timeOfDay.put(0, "Ночь");

        gameMode.put(0, "Deathmatch");
        gameMode.put(1, "Командный");
        gameMode.put(2, "Захват флага");
    }

    @RequestMapping("/maps")
    public String getMaps(ModelMap model, HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            model.addAttribute("username", request.getUserPrincipal().getName());
        }
        else {
            model.addAttribute("username", null);
        }
        List<Map> maps = new ArrayList<Map>(mapRepository.findAll());
        List<ViewMap> viewMaps = new ArrayList<ViewMap>();
        for (int i = 0; i < maps.size(); i++) {
            viewMaps.add(createViewMap(maps.get(i)));
        }
        model.addAttribute("maps", viewMaps);
        return "maps";
    }

    public ViewMap createViewMap(Map map) {
        ArrayList<String> weatherEffectsList = new ArrayList<String>();
        if (map.getWeatherEffects() != null) {
            for (int i = 0; i < map.getWeatherEffects().length; i++) {
                weatherEffectsList.add(weatherEffects.get(map.getWeatherEffects()[i]));
            }
        }
        return new ViewMap(map.getMapId(), map.getTitle(), size.get(map.getSize()), weatherEffectsList,
                timeOfDay.get(map.getTimeOfDay()), gameMode.get(map.getGameMode()),  map.getDescription(),
                map.getDateOfAddition(), map.getRating());
    }

    @RequestMapping("/maps/{id}")
    public String getMapById(ModelMap model, @PathVariable("id") long id, HttpServletRequest request)
    {
        if (request.getUserPrincipal() != null) {
            model.addAttribute("username", request.getUserPrincipal().getName());
        }
        else {
            model.addAttribute("username", null);
        }
        Map map = mapRepository.findAll().get((int) id - 1);
        model.addAttribute("map", createViewMap(map));
        return "map";
    }
}