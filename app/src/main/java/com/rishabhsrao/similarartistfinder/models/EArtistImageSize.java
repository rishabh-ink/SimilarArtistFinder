package com.rishabhsrao.similarartistfinder.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum EArtistImageSize {
  SMALL,
  MEDIUM,
  LARGE,
  EXTRA_LARGE,
  MEGA;

  private static Map<String, EArtistImageSize> imageSizeMap = new HashMap<String, EArtistImageSize>();

  static {
    imageSizeMap.put("small", SMALL);
    imageSizeMap.put("medium", MEDIUM);
    imageSizeMap.put("large", LARGE);
    imageSizeMap.put("extralarge", EXTRA_LARGE);
    imageSizeMap.put("mega", MEGA);
  }

  @JsonValue
  public String toValue() {
    for (Map.Entry<String, EArtistImageSize> entry : imageSizeMap.entrySet()) {
      if(entry.getValue() == this) {
        return entry.getKey();
      }
    }

    return null;
  }

  @JsonCreator
  public static EArtistImageSize forValue(String value) {
    return imageSizeMap.get(value.toLowerCase());
  }
}
