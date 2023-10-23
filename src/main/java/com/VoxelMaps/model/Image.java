package com.VoxelMaps.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Image {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;
    public String name;
    public String type;

    public byte[] imageData;

    public Image(String originalFilename, String contentType, byte[] bytes){
        name = originalFilename;
        type = contentType;
        imageData = bytes;
    }

    public byte[] getImageData() {
        return imageData;
    }
}