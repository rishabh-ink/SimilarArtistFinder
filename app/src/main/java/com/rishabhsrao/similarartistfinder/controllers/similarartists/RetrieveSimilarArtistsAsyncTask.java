package com.rishabhsrao.similarartistfinder.controllers.similarartists;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rishabhsrao.similarartistfinder.models.SelectedArtist;
import com.rishabhsrao.similarartistfinder.settings.Settings;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RetrieveSimilarArtistsAsyncTask extends AsyncTask<String, Integer, SelectedArtist> {
  private Context context;
  private ProgressDialog progressDialog;

  public RetrieveSimilarArtistsAsyncTask() {
  }

  public RetrieveSimilarArtistsAsyncTask(Context context) {
    this.context = context;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();

    Log.d(RetrieveSimilarArtistsAsyncTask.class.getSimpleName(), "Showing progress dialog...");
    this.progressDialog = ProgressDialog.show(this.context, "Searching", "Please wait...");
  }

  @Override
  protected SelectedArtist doInBackground(String... artistName) {
    Uri.Builder uri = Settings.API_URI.appendQueryParameter("artistName", artistName[0]);
    Log.d(RetrieveSimilarArtistsAsyncTask.class.getSimpleName(), "Built URL: " + uri.toString());

    JsonNode jsonNode;
    HttpURLConnection urlConnection = null;
    SelectedArtist selectedArtist = null;

    try {
      URL url = new URL(uri.toString());
      urlConnection = (HttpURLConnection) url.openConnection();

      BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());

      ObjectMapper objectMapper = new ObjectMapper();
      jsonNode = objectMapper.readTree(bufferedInputStream);

      ArtistConstructor artistConstructor = new ArtistConstructor();
      selectedArtist = artistConstructor.parseArtistInfo(jsonNode);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    } finally {
      Log.e(RetrieveSimilarArtistsAsyncTask.class.getSimpleName(), "Disconnecting...");
      urlConnection.disconnect();
    }

    return selectedArtist;
  }

  @Override
  protected void onPostExecute(SelectedArtist selectedArtist) {
    super.onPostExecute(selectedArtist);

    Log.d(RetrieveSimilarArtistsAsyncTask.class.getSimpleName(), "Dismissing progress dialog...");
    this.progressDialog.dismiss();

//    Log.d(RetrieveSimilarArtistsAsyncTask.class.getSimpleName(), "Retrieved JSON: " + jsonNode.toString());
  }

  public Context getContext() {
    return context;
  }

  public void setContext(Context context) {
    this.context = context;
  }

  public ProgressDialog getProgressDialog() {
    return progressDialog;
  }

  public void setProgressDialog(ProgressDialog progressDialog) {
    this.progressDialog = progressDialog;
  }
}
