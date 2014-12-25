package com.rishabhsrao.similarartistfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {
  @JsonProperty(value="name")
  private String name;

  @JsonProperty(value="url")
  private URL url;

  public Tag(String name, URL url) {
    this.setName(name);
    this.setUrl(url);
  }

  public Tag() {
  }

  public URL getUrl() {
    return url;
  }

  public void setUrl(URL url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
