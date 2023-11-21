package com.VoxelMaps.model;

import java.sql.Date;
import java.util.ArrayList;

public class ViewMap {
    public int Id;

    public String Title;

    public String Size;

    public ArrayList<String> WeatherEffects;

    public String TimeOfDay;

    public String GameMode;

    public String Description;

    public Date DateOfAddition;

    public int Rating;

    public byte[] Image;

    public byte[] Map;

    public String Author;

    public ViewMap(int id, String title, String size, ArrayList<String> weatherEffects, String timeOfDay,
                   String gameMode, String description, Date dateOfAddition, int rating, String author) {
        Id = id;
        Title = title;
        Size = size;
        WeatherEffects = weatherEffects;
        TimeOfDay = timeOfDay;
        GameMode = gameMode;
        Description = description;
        DateOfAddition = dateOfAddition;
        Rating = rating;
        Author = author;
    }
}
