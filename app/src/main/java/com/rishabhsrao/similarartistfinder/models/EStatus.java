package com.rishabhsrao.similarartistfinder.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum EStatus {
  OK,
  FAILED;

  private static Map<String, EStatus> statusMap = new HashMap<String, EStatus>();

  static {
    statusMap.put("ok", OK);
    statusMap.put("failed", FAILED);
  }

  @JsonValue
  public String toValue() {
    for (Map.Entry<String, EStatus> entry : statusMap.entrySet()) {
      if(entry.getValue() == this) {
        return entry.getKey();
      }
    }

    return null;
  }

  @JsonCreator
  public static EStatus forValue(String value) {
    return statusMap.get(value.toLowerCase());
  }
}
