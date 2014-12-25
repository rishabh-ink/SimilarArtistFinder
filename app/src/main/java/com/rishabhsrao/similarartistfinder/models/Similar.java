package com.rishabhsrao.similarartistfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Similar {
  @JsonProperty("artist")
  private ArrayList<Artist> artists;

  public Similar() {
  }

  public Similar(ArrayList<Artist> artists) {
    this.artists = artists;
  }

  public ArrayList<Artist> getArtists() {
    return artists;
  }

  public void setArtists(ArrayList<Artist> artists) {
    this.artists = artists;
  }

  @Override
  public String toString() {
    return "Similar{" +
      "artists=" + artists +
      '}';
  }
}
