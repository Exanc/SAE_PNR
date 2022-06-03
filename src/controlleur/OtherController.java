package controlleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import vue.EView;

public class OtherController {
    
    @FXML
    private void switchViewAction(ActionEvent event) {
        ViewSwitcher.switchTo(EView.CONNEXION);
    }
}
