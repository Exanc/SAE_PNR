package controlleur;

import javafx.fxml.FXML;
import vue.EView;

public class Consultation {
    
    @FXML
    public void disconnectAction () {
        modele.traitement.ConnectionFactory.setProperties("", "", null);
        ViewSwitcher.switchTo(EView.CONNEXION);
    }

    @FXML
    public void btPreferences () {
        // TODO: pop-up ?
    }

    @FXML
    public void btRetour () {
        ViewSwitcher.switchTo(EView.MENU);
    }

    //TODO: tables...
}
