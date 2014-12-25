package com.rishabhsrao.similarartistfinder.controllers.similarartists;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.rishabhsrao.similarartistfinder.R;
import com.rishabhsrao.similarartistfinder.settings.Settings;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SimilarArtistsActivity extends Activity {
  private String artistName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_similar_artists);

    ActionBar actionBar = getActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);

    Intent searchArtistIntent = this.getIntent();
    this.artistName = searchArtistIntent.getStringExtra(Settings.INTENT_EXTRA_ARTIST_NAME);

    Log.d(SimilarArtistsActivity.class.getName(), "Searching for " + this.artistName);
    try {
      JSONObject artistInfo = new RetrieveSimilarArtistsAsyncTask().execute(this.artistName).get();
      TextView artistBio = (TextView) this.findViewById(R.id.activity_similar_artists_artistbio);

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
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
}
