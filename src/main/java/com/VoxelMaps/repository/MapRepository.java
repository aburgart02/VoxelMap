package com.VoxelMaps.repository;

import com.VoxelMaps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.VoxelMaps.model.Map;
import org.yaml.snakeyaml.events.Event;

public interface MapRepository extends JpaRepository<Map, Long> {

}
