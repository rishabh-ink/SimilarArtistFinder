package com.rishabhsrao.similarartistfinder.settings;

import android.net.Uri;

public class Settings {
  public static final String INTENT_EXTRA_ARTIST_NAME = "intent_extra__artist_name";
  public static final Uri.Builder API_URI = new Uri.Builder()
    .scheme("http")
    .authority("pipes.yahoo.com")
    .appendPath("pipes")
    .appendPath("pipe.run")
    .appendQueryParameter("_id", "a1f7b1e60ad0c5881db7fa9ff2f3698a")
    .appendQueryParameter("_render", "json");
}
