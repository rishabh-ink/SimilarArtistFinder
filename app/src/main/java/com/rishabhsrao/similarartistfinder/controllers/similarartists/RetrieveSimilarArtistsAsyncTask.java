package com.rishabhsrao.similarartistfinder.controllers.similarartists;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rishabhsrao.similarartistfinder.settings.Settings;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RetrieveSimilarArtistsAsyncTask extends AsyncTask<String, Integer, JsonNode> {
  @Override
  protected JsonNode doInBackground(String... artistName) {
    Uri.Builder uri = Settings.API_URI.appendQueryParameter("artistName", artistName[0]);
    Log.d(RetrieveSimilarArtistsAsyncTask.class.getSimpleName(), "Built URL: " + uri.toString());

    JsonNode jsonNode = null;
    HttpURLConnection urlConnection = null;

    try {
      URL url = new URL(uri.toString());
      urlConnection = (HttpURLConnection) url.openConnection();

      BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());

      ObjectMapper objectMapper = new ObjectMapper();
      jsonNode = objectMapper.readTree(bufferedInputStream);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    } finally {
      Log.e(RetrieveSimilarArtistsAsyncTask.class.getSimpleName(), "Disconnecting...");
      urlConnection.disconnect();
    }

    return jsonNode;
  }

  @Override
  protected void onPostExecute(JsonNode jsonNode) {
    super.onPostExecute(jsonNode);
    Log.e(RetrieveSimilarArtistsAsyncTask.class.getSimpleName(), "Retrieved JSON: " + jsonNode.toString());
  }
}
