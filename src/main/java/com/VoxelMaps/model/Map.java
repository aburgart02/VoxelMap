package com.VoxelMaps.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Maps")
public class Map {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "MapId")
  public int MapId;

  @Column(name = "Title")
  public String Title;

  public Map() {

  }

  public Map(String title) {
    this.Title = title;
  }

  public String GetTitle() {
      return Title;
  }
}
