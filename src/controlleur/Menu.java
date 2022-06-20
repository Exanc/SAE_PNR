package controlleur;

import javafx.fxml.FXML;
import vue.EView;

public class Menu
{
    @FXML
    public void disconnectAction () {
        modele.traitement.ConnectionFactory.setProperties("", "", null);
        ViewSwitcher.switchTo(EView.CONNEXION);
    }

    @FXML
    public void btConsulter () {
        ViewSwitcher.switchTo(EView.CONSULTATION);
    }

    @FXML
    public void btAdmin () {
        //TODO
    }

    @FXML
    public void btSaisir () {
        //TODO
    }

    @FXML
    public void btImporter () {
        ViewSwitcher.switchTo(EView.IMPORTATION);
    }
}
