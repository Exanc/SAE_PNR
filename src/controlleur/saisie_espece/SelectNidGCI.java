package controlleur.saisie_espece;

import controlleur.NumericField;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lib.DynamicTable;
import modele.traitement.SQLQuerys;

public class SelectNidGCI {
    
    @FXML TextField fId_Select, fPlage_Select, fId_Add, fPlage_Add;
    @FXML VBox box;

    public static final String request = "SELECT * FROM Nid_GCI";

    /**
     * Initialisation de l'interface
     */
    public void initialize () {
        fId_Select.textProperty().addListener(NumericField.onlyDigit(fId_Select));
        fId_Add.textProperty().addListener(NumericField.onlyDigit(fId_Add));

        this.showTable(request);
    }

    /**
     * Selection d'un nid
     */
    public void select () {
        SaisieGCI.Instance.changeSelectedNid(fId_Select.getText(), fPlage_Select.getText());
    }

    /**
     * Ajout d'un nid
     */
    public void add () {

        String SQL = "";

        SQL += "INSERT INTO Nid_GCI ";
        SQL += "VALUES ("+fId_Add.getText()+", "+fPlage_Add.getText()+", null, null, null, null, null);";

        SQLQuerys.executeSQLScript(SQL);

        this.showTable(request);
    }

    /**
     * Méthode d'affichage du résultat d'une requéte SQL
     * @param SQL la requéte SQL
     */
    public void showTable (String SQL) {
        
        int i = 0;
        boolean exists = false;
        for (Node n : this.box.getChildren()) {

            if (n.getId() == "table") {
                exists = true;
                break;
            }
            i++;
        }

        if (exists)
            this.box.getChildren().remove(i);

        DynamicTable t = new DynamicTable(SQL);
        TableView table = t.getTable();
        table.setId("table");

        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);

        this.box.getChildren().add(table);
    }
}
