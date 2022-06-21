package controlleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class NumericField {
    public static ChangeListener<String> onlyDigit(TextField textField) {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (!arg2.matches("\\d*")) {
                    textField.setText(arg2.replaceAll("[^\\d]", ""));
                }
            }
        };
    }
}
