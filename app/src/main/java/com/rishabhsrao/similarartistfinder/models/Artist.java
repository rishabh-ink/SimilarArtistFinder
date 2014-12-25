package com.rishabhsrao.similarartistfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {
  private String name;
  private URL url;

  @JsonProperty(value="image")
  private ArrayList<ArtistImage> images;

  public Artist(String name, String url, ArrayList<ArtistImage> images) throws MalformedURLException {
    this.setName(name);
    this.setUrl(url);
    this.setImages(images);
  }

  public Artist() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public URL getUrl() {
    return url;
  }

  public void setUrl(String url) throws MalformedURLException {
    this.url = new URL(url);
  }

  public ArrayList<ArtistImage> getImages() {
    return images;
  }

  public void setImages(ArrayList<ArtistImage> images) {
    this.images = images;
  }

  @Override
  public String toString() {
    StringBuilder artist = new StringBuilder();

    artist.append(this.name);
    artist.append(" ");
    artist.append("(" + this.url + ")");
    artist.append(" ");
    artist.append(super.toString());

    return artist.toString();
  }
}
