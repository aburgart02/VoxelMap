package com.VoxelMaps.controller;

import com.VoxelMaps.service.MapFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/download-map-file")
public class MapFileDownloadController {
    @Autowired
    private MapFileService mapFileService;


    @GetMapping("/{mapFileId}")
    public ResponseEntity<?> downloadMapFile(@PathVariable String mapFileId) {
        byte[] mapFile = mapFileService.downloadMapFile(Integer.parseInt(mapFileId));
        String fileName = mapFileService.getMapName(Integer.parseInt(mapFileId));
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/octet-stream"))
                .header("content-disposition", "attachment; filename=" + fileName)
                .body(mapFile);
    }
}