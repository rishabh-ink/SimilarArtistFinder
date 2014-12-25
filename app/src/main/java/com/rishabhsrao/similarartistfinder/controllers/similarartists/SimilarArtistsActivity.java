package com.rishabhsrao.similarartistfinder.controllers.similarartists;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.rishabhsrao.similarartistfinder.R;
import com.rishabhsrao.similarartistfinder.models.Artist;
import com.rishabhsrao.similarartistfinder.settings.Settings;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class SimilarArtistsActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_similar_artists);

    ActionBar actionBar = getActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);

    this.fetchArtistInfo();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_similar_artists, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    switch (id) {
      case R.id.action_settings: {
        return true;
      }

      case android.R.id.home: {
        finish();
      }

      default: {
      }
    }

    return super.onOptionsItemSelected(item);
  }

  public void fetchArtistInfo() {
    Intent searchArtistIntent = this.getIntent();
    String artistName = searchArtistIntent.getStringExtra(Settings.INTENT_EXTRA_ARTIST_NAME);
    JsonNode artistInfo;

    Log.d(SimilarArtistsActivity.class.getName(), "Searching for " + artistName);
    try {
      artistInfo = new RetrieveSimilarArtistsAsyncTask().execute(artistName).get();
      this.parseArtistInfo(artistInfo);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void parseArtistInfo(JsonNode artistInfo) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayNode items = (ArrayNode) artistInfo.path("value").path("items").path(0).path("artist").path("similar").path("artist");

    Iterator<JsonNode> jsonNodeIterator = items.elements();

    while (jsonNodeIterator.hasNext()) {
      Artist artist = objectMapper.readValue(jsonNodeIterator.next().traverse(), Artist.class);

      Log.d(SimilarArtistsActivity.class.getSimpleName(), "Similar artist: " + artist);
    }
  }
}
