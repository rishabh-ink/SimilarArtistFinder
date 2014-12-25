package com.rishabhsrao.similarartistfinder.controllers.similarartists;

import android.content.Context;
import android.util.Log;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rishabhsrao.similarartistfinder.models.EStatus;
import com.rishabhsrao.similarartistfinder.models.SelectedArtist;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ArtistConstructor {
  private Context context;

  public ArtistConstructor() {
  }

  public ArtistConstructor(Context context) {
    this.context = context;
  }

  public SelectedArtist fetchSelectedArtist(final String artistName) {
    JsonNode artistInfo;
    SelectedArtist selectedArtist = null;

    Log.d(ArtistConstructor.class.getSimpleName(), "Searching for " + artistName);
    try {
      artistInfo = new RetrieveSimilarArtistsAsyncTask().execute(artistName).get();
      selectedArtist = this.parseArtistInfo(artistInfo);
      return selectedArtist;
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return selectedArtist;
  }

  private SelectedArtist parseArtistInfo(JsonNode artistInfo) throws IOException {
    SelectedArtist selectedArtist;
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode itemsFirst = artistInfo.path("value").path("items").path(0);

    EStatus status;
    status = objectMapper.readValue(itemsFirst.path("status").traverse(), EStatus.class);

    switch (status) {
      case OK: {
        selectedArtist = objectMapper.readValue(
          itemsFirst.path("artist").traverse(),
          SelectedArtist.class
        );

        Log.d(ArtistConstructor.class.getSimpleName(), "Selected artist: " + selectedArtist.getName());
        return selectedArtist;
      }

      case FAILED: {
        Log.e(ArtistConstructor.class.getSimpleName(), "Artist not found");
        return null;
      }

      default: {
        Log.e(ArtistConstructor.class.getSimpleName(), "Artist not found");
        return null;
      }
    }
  }

  public Context getContext() {
    return context;
  }

  public void setContext(Context context) {
    this.context = context;
  }
}
