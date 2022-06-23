package controlleur.saisie_espece;

import controlleur.NumericField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lib.DynamicTable;
import modele.donnee.EspeceChouette;
import modele.donnee.Sexe;
import modele.traitement.SQLQuerys;

public class SelectChouette {
    
    @FXML TextField fIdSelect, fIdAdd;
    @FXML ComboBox cbSelectEspece, cbSelectSexe, cbAddEspece, cbAddSexe;
    @FXML VBox box;

    public static final String request = "SELECT * FROM Chouette";

    /**
     * Initialisation de l'interface
     */
    public void initialize () {

        cbSelectEspece.setItems(FXCollections.observableArrayList(EspeceChouette.values()));
        cbAddEspece.setItems(FXCollections.observableArrayList(EspeceChouette.values()));
        cbAddSexe.setItems(FXCollections.observableArrayList(Sexe.values()));
        cbSelectSexe.setItems(FXCollections.observableArrayList(Sexe.values()));

        fIdSelect.textProperty().addListener(NumericField.onlyDigit(fIdSelect));
        fIdAdd.textProperty().addListener(NumericField.onlyDigit(fIdAdd));

        this.showTable(request);
    }

    /**
     * Selection d'une chouette
     */
    public void select () {
        SaisieChouette.Instance.changeSelectedInd(fIdSelect.getText(), ((EspeceChouette) cbSelectEspece.getValue()).getValue(), ((Sexe) cbSelectSexe.getValue()).getValue().toUpperCase());
    }

    /**
     * Ajout d'une chouette
     */
    public void add () {

        String SQL = "";

        SQL += "INSERT INTO Chouette ";
        SQL += "VALUES("+fIdSelect.getText()+", "+((EspeceChouette) cbSelectEspece.getValue()).getValue()+", "+((Sexe) cbSelectSexe.getValue()).getValue().toUpperCase()+");";

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
