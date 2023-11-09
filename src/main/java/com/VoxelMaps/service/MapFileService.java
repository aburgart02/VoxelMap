package com.VoxelMaps.service;
import com.VoxelMaps.model.MapFile;
import com.VoxelMaps.repository.MapFileRepository;
import com.VoxelMaps.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class MapFileService {

    @Autowired
    private MapFileRepository mapFileRepository;

    public String uploadMapFile(MultipartFile mapFile) throws IOException {
        var mapFileToSave = new MapFile(mapFile.getOriginalFilename(), mapFile.getContentType(),
                FileUtils.compressFile(mapFile.getBytes()));
        mapFileRepository.save(mapFileToSave);
        return "file uploaded: " + mapFile.getOriginalFilename();
    }

    public byte[] downloadMapFile(int mapFileId) {
        Optional<MapFile> dbMapFiles = Optional.ofNullable(mapFileRepository.findAll().get(mapFileId - 1));
        return dbMapFiles.map(mapFile -> {
            try {
                return FileUtils.decompressFile(mapFile.getMapFile());
            } catch (DataFormatException | IOException exception) {
                System.out.println("error");
            }
            return null;
        }).orElse(null);
    }

    public String getMapName(int mapFileId) {
        MapFile dbMapFiles = mapFileRepository.findAll().get(mapFileId - 1);
        return dbMapFiles.getName();
    }
}