package com.rishabhsrao.similarartistfinder.controllers.similarartists;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.rishabhsrao.similarartistfinder.settings.Settings;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RetrieveSimilarArtistsAsyncTask extends AsyncTask<String, Integer, JSONObject> {
  @Override
  protected JSONObject doInBackground(String... artistName) {
    Uri.Builder uri = Settings.API_URI.appendQueryParameter("artistName", artistName[0]);
    Log.d(RetrieveSimilarArtistsAsyncTask.class.getSimpleName(), "Built URL: " + uri.toString());

    JSONObject inputJsonObject = null;
    HttpURLConnection urlConnection = null;

    try {
      URL url = new URL(uri.toString());
      urlConnection = (HttpURLConnection) url.openConnection();
      BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());

      String inputString = this.readStream(bufferedInputStream);

      Log.d(RetrieveSimilarArtistsAsyncTask.class.getSimpleName(), "Obtained stream: " + inputString);
      inputJsonObject = new JSONObject(inputString);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (JSONException e) {
      e.printStackTrace();
    }
    finally {
      Log.e(RetrieveSimilarArtistsAsyncTask.class.getSimpleName(), "Disconnecting...");
      urlConnection.disconnect();
    }

    return inputJsonObject;
  }

  @Override
  protected void onPostExecute(JSONObject jsonObject) {
    super.onPostExecute(jsonObject);
    Log.e(RetrieveSimilarArtistsAsyncTask.class.getSimpleName(), "Retrieved JSON: " + jsonObject.toString());
  }

  private String readStream(BufferedInputStream bufferedInputStream) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
    String line;
    StringBuilder stringBuilder = new StringBuilder();

    while(null != (line = bufferedReader.readLine())) {
      stringBuilder.append(line);
    }

    return stringBuilder.toString();
  }
}
