package com.rishabhsrao.similarartistfinder.controllers.similarartists;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.rishabhsrao.similarartistfinder.R;
import com.rishabhsrao.similarartistfinder.models.SelectedArtist;
import com.rishabhsrao.similarartistfinder.settings.Settings;

import java.util.concurrent.ExecutionException;

public class SimilarArtistsActivity extends Activity {
  private SelectedArtist selectedArtist;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_similar_artists);

    ActionBar actionBar = getActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);

    Intent searchArtistIntent = this.getIntent();
    String artistName = searchArtistIntent.getStringExtra(Settings.INTENT_EXTRA_ARTIST_NAME);

    this.selectedArtist = this.fetchSelectedArtist(artistName);
    Log.d(SimilarArtistsActivity.class.getSimpleName(), "Selected artist: " + this.selectedArtist);
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

  public SelectedArtist fetchSelectedArtist(final String artistName) {
    SelectedArtist selectedArtist = null;

    Log.d(ArtistConstructor.class.getSimpleName(), "Searching for " + artistName);
    try {
      selectedArtist = new RetrieveSimilarArtistsAsyncTask(SimilarArtistsActivity.this).execute(artistName).get();
      return selectedArtist;
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    return selectedArtist;
  }
}
