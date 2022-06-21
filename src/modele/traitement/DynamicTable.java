package modele.traitement;

import java.sql.Connection;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * 
 * @author Narayan
 */

public class DynamicTable {

    private ObservableList<ObservableList> data;
    private TableView tableview;

    public DynamicTable (String command) {

        tableview = new TableView();
        data = FXCollections.observableArrayList();

        try {
            ResultSet rs = modele.traitement.SQLQuerys.executeSQL(command);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                // We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(
                        new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {

                                Object o = param.getValue().get(j);
                                String str = "(null)";

                                if (o != null) str = o.toString();

                                return new SimpleStringProperty(str);
                            }
                        });

                tableview.getColumns().addAll(col);
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }

            // FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {

            controlleur.ErrorHandler.show(
                "Erreur lors de la création de la table.",
                e.getMessage(), e);
        }
    }

    public TableView getTable () {
        return tableview;
    }
}