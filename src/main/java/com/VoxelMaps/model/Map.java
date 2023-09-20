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
  public String title;

  public Map() {

  }

  public Map(String title) {
    this.title = title;
  }

  public String GetTitle() {
      return title;
  }
}
