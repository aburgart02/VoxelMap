package com.VoxelMaps.service;

import com.VoxelMaps.model.Map;
import com.VoxelMaps.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;

@Service
public class MapService {
    @Autowired
    MapRepository mapRepository;

    //TODO Отрефакторить метод
    public boolean saveMap(Map map) {
        mapRepository.save(map);
        return true;
    }
}