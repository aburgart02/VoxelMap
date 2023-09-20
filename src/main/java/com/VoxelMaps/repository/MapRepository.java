package com.VoxelMaps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.VoxelMaps.model.Map;

public interface MapRepository extends JpaRepository<Map, Long> {

  List<Map> findByTitleContaining(String Title);
}
