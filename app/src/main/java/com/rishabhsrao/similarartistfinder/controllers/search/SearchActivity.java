package com.rishabhsrao.similarartistfinder.controllers.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.rishabhsrao.similarartistfinder.R;
import com.rishabhsrao.similarartistfinder.controllers.similarartists.SimilarArtistsActivity;
import com.rishabhsrao.similarartistfinder.validators.EditTextValidator;


public class SearchActivity extends Activity {
  private Button searchButton;
  private EditText artistNameEditText;

  private View.OnClickListener searchButtonClickListener;

  public SearchActivity() {
    this.searchButtonClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        EditTextValidator editTextValidator = new EditTextValidator();
        if(editTextValidator.validate(SearchActivity.this.artistNameEditText)) {
          Intent searchArtistIntent = new Intent(SearchActivity.this, SimilarArtistsActivity.class);
        }
      }
    };
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    this.searchButton = (Button) this.findViewById(R.id.activity_search_button_search);
    this.artistNameEditText = (EditText) this.findViewById(R.id.activity_search_edittext_artistname);

    this.searchButton.setOnClickListener(this.searchButtonClickListener);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_search, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
