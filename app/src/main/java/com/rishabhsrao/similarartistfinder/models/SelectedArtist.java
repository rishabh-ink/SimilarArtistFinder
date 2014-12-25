package com.rishabhsrao.similarartistfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.MalformedURLException;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SelectedArtist extends Artist {
  @JsonProperty(value="similar")
  Similar similarArtists;

  @JsonProperty(value="bio")
  ArtistBio bio;

  public SelectedArtist() {
    super();
  }

  public SelectedArtist(String name, String url, ArrayList<ArtistImage> images, Similar similarArtists, ArtistBio bio) throws MalformedURLException {
    super(name, url, images);
    this.setBio(bio);
    this.setSimilarArtists(similarArtists);
  }

  public ArtistBio getBio() {
    return bio;
  }

  public void setBio(ArtistBio bio) {
    this.bio = bio;
  }

  public Similar getSimilarArtists() {
    return similarArtists;
  }

  public void setSimilarArtists(Similar similarArtists) {
    this.similarArtists = similarArtists;
  }

  @Override
  public String toString() {
    return "SelectedArtist{" +
      "similarArtists=" + similarArtists +
      ", bio=" + bio +
      '}';
  }
}
