package controlleur;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.traitement.SQLQuerys;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlleur.*;
import vue.ERole;
import vue.EView;

public class DefaultUser {
    @FXML private ComboBox cbRole;
    @FXML private Label lUsername;

    public void initialize() {
        cbRole.setItems(FXCollections.observableArrayList(ERole.values()));
    }

    public void setUser(String username, ERole role) {
        cbRole.setValue(role);
        lUsername.setText(username);
    }
}
