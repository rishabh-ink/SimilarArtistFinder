package com.rishabhsrao.similarartistfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistBio {
  @JsonProperty(value="summary")
  private String summary;

  @JsonProperty(value="content")
  private String content;

  @JsonProperty(value="placeformed")
  private String placeFormed;

  public ArtistBio() {
  }

  public ArtistBio(String summary, String content, String placeFormed) {
    this.setSummary(summary);
    this.setContent(content);
    this.setPlaceFormed(placeFormed);
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPlaceFormed() {
    return placeFormed;
  }

  public void setPlaceFormed(String placeFormed) {
    this.placeFormed = placeFormed;
  }

  @Override
  public String toString() {
    return "ArtistBio{" +
      "summary='" + summary + '\'' +
      ", content='" + content + '\'' +
      ", placeFormed='" + placeFormed + '\'' +
      '}';
  }
}
