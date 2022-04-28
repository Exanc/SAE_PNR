package Vue.Controllers;

import Vue.EView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OtherController {
    
    @FXML
    private void switchViewAction(ActionEvent event) {
        Vue.ViewSwitcher.switchTo(EView.MAIN);
    }
}
