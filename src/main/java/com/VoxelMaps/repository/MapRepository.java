package com.VoxelMaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.VoxelMaps.model.Map;

public interface MapRepository extends JpaRepository<Map, Long> {

}
