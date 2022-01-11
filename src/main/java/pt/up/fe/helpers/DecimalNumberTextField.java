package pt.up.fe.helpers;


import javafx.scene.control.TextField;

public class DecimalNumberTextField extends TextField {

  @Override
  public void replaceText(int start, int end, String text) {
    if (validate(super.getText().concat(text))) {
      super.replaceText(start, end, text);
    }
  }

  @Override
  public void replaceSelection(String text) {
    // Not allowed
    return;
  }

  private boolean validate(String text) {
    if (text.length() == 1) {
      return text.matches("-?[0-9]?");
    }
    return text.matches("-?[0-9]+(.?[0-9]*)?");
  }
}


