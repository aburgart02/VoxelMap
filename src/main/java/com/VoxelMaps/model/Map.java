package com.VoxelMaps.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "maps")
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name = "title")
    public String Title;

    @Column(name = "size")
    public int Size;

    @Column(name = "weatherEffects")
    public int[] WeatherEffects;

    @Column(name = "timeOfDay")
    public int TimeOfDay;

    @Column(name = "gameMode")
    public int GameMode;

    @Column(name = "description")
    public String Description;

    @Column(name = "dateOfAddition")
    public Date DateOfAddition;

    @Column(name = "rating")
    public int Rating;

    @Column(name = "image")
    public byte[] Image;

    @Column(name = "map")
    public byte[] Map;

    @ManyToOne(fetch = FetchType.LAZY)
    private User Author;

    public Map() {
    }

    public int getMapId() {
        return Id;
    }

    public void setMapId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public int[] getWeatherEffects() {
        return WeatherEffects;
    }

    public void setWeatherEffects(int[] weatherEffects) {
        WeatherEffects = weatherEffects;
    }

    public int getTimeOfDay() {
        return TimeOfDay;
    }

    public void setTimeOfDay(int timeOfDay) {
        TimeOfDay = timeOfDay;
    }

    public int getGameMode() {
        return GameMode;
    }

    public void setGameMode(int gameMode) {
        GameMode = gameMode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getDateOfAddition() {
        return DateOfAddition;
    }

    public void setDateOfAddition(Date dateOfAddition) {
        DateOfAddition = dateOfAddition;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public byte[] getImage() { return Image; }
    public void setImage(byte[] image) {
        Image = image;
    }

    public byte[] getMap() { return Map; }
    public void setMap(byte[] map) {
        Map = map;
    }

    public User getAuthor() { return Author; }
    public void setAuthor(User author) {
        Author = author;
    }
}