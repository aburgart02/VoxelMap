package com.VoxelMaps.controller;

import com.VoxelMaps.repository.MapRepository;
import com.VoxelMaps.model.Map;
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
    public String getMaps(ModelMap model) {
        List<Map> maps = new ArrayList<Map>(mapRepository.findAll());
        model.addAttribute("maps", maps);
        return "maps";
    }

    @RequestMapping("/maps/{id}")
    public String getMapById(ModelMap model, @PathVariable("id") long id)
    {
        Map map = mapRepository.findAll().get((int) id - 1);
        ArrayList<String> weatherEffectsList = new ArrayList<String>();
        if (map.getWeatherEffects() != null) {
            for (int i = 0; i < map.getWeatherEffects().length; i++) {
                weatherEffectsList.add(weatherEffects.get(map.getWeatherEffects()[i]));
            }
        }
        model.addAttribute("map", map);
        model.addAttribute("size", size.get(map.getSize()));
        model.addAttribute("weatherEffects", weatherEffectsList);
        model.addAttribute("timeOfDay", timeOfDay.get(map.getTimeOfDay()));
        model.addAttribute("gameMode", gameMode.get(map.getGameMode()));
        return "map";
    }
}