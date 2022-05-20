package controlleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

import vue.EView;

/**
 * Controller de la vue ui.fxml
 */
public class Controller {
    
    @FXML
    private Text output;

    private String operator;
    private String a;

    /**
     * Gére les interactions avec la pavé numérique
     * @param event
     */
    @FXML
    private void processNumpad(ActionEvent event) {

        String value = ((Button)event.getSource()).getText();
        output.setText(output.getText() + value);
    }

    /**
     * Gére les interactions avec les butons opérateurs
     * @param event
     */
    @FXML
    private void processOperator(ActionEvent event) {
        
        String value = ((Button)event.getSource()).getText();

        if (! value.equals("=")) {
            operator = value;

            if (! output.getText().equals("")) {
                a = output.getText();
                output.setText("");
            }
            
        } else {
            output.setText("" + modele.Calculator.calculate(Long.parseLong(a), Long.parseLong(output.getText()), operator));
        }
    }

    /**
     * Gére l'intéraction avec la bouton de changement de vue
     * @param event
     */
    @FXML
    private void switchViewAction(ActionEvent event) {
        ViewSwitcher.switchTo(EView.OTHER);
    }
}
