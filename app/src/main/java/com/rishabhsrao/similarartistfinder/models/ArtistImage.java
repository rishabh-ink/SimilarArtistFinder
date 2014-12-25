package com.rishabhsrao.similarartistfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistImage {
  private EArtistImageSize size;
  private URL content;

  public ArtistImage(EArtistImageSize size, URL content) {
    this.setSize(size);
    this.setContent(content);
  }

  public ArtistImage() {
  }

  public EArtistImageSize getSize() {
    return size;
  }

  public void setSize(EArtistImageSize size) {
    this.size = size;
  }

  public URL getContent() {
    return content;
  }

  public void setContent(URL content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "ArtistImage{" +
      "size=" + size +
      ", content=" + content +
      '}';
  }
}
