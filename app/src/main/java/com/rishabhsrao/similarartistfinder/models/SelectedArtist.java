package com.rishabhsrao.similarartistfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SelectedArtist extends Artist {
  @JsonProperty(value="similar")
  ArrayList<Artist> similar;

  @JsonProperty(value="bio")
  ArtistBio bio;
}
