package com.rishabhsrao.similarartistfinder.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Calendar;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistBio {
  @JsonProperty(value="summary")
  private String summary;

  @JsonProperty(value="content")
  private String content;

  @JsonProperty(value="placeformed")
  private String placeFormed;

  @JsonProperty(value="yearformed")
  private Calendar yearFormed;

  public ArtistBio(String summary, String content, String placeFormed, String yearFormed) {
    this.setSummary(summary);
    this.setContent(content);
    this.setPlaceFormed(placeFormed);
    this.setYearFormed(yearFormed);
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

  @JsonValue
  public Calendar getYearFormed() {
    return yearFormed;
  }

  @JsonCreator
  public void setYearFormed(String yearFormed) {
    int yearFormedInt;

    try {
      yearFormedInt = Integer.parseInt(yearFormed, 10);

    } catch (NumberFormatException e) {
      yearFormedInt = 1970;
    }

    this.yearFormed.set(Calendar.YEAR, yearFormedInt);
  }
}
