package GUI.Controllers;

import GUI.EView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OtherController {
    
    @FXML
    private void switchViewAction(ActionEvent event) {
        GUI.ViewSwitcher.switchTo(EView.MAIN);
    }
}
