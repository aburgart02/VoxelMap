package com.VoxelMaps.repository;

import com.VoxelMaps.model.MapFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MapFileRepository extends JpaRepository<MapFile, Long> {
    Optional<MapFile> findByName(String name);
}