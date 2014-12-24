package com.rishabhsrao.similarartistfinder.validators;

import android.widget.EditText;

public class EditTextValidator implements IValidator<EditText> {
  @Override
  public boolean validate(EditText text) {
    if(text.getText().toString().trim().isEmpty()) {
      return false;
    } else {
      return true;
    }
  }
}
