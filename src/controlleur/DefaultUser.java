package controlleur;

import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;

import java.util.Optional;

import controlleur.*;
import vue.ERole;
import vue.EView;

public class DefaultUser {

    @FXML private ComboBox cbRole;
    @FXML private Label lUsername;

    public void initialize () {
        cbRole.setItems(FXCollections.observableArrayList(ERole.values()));
    }

    public void setUser (String username, ERole role) {
        cbRole.setValue(role);
        lUsername.setText(username);
    }

    public void btChangePassword () {
        
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");

        // Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField password = new TextField();
        password.setPromptText("Mot de passe");
        TextField c_password = new TextField();
        c_password.setPromptText("Confirmer");

        grid.add(new Label("Nouveau mot de passe:"), 0, 0);
        grid.add(password, 1, 0);
        grid.add(new Label("Confirmer le nouveau mot de passe:"), 0, 1);
        grid.add(c_password, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node confirmButton = dialog.getDialogPane().lookupButton(ButtonType.APPLY);
        confirmButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(!password.getText().equals(c_password.getText()));
            confirmButton.setDisable(password.getText().isEmpty());
        });
        
        c_password.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(!password.getText().equals(c_password.getText()));
            confirmButton.setDisable(password.getText().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> password.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.APPLY) {
                return new Pair<>(password.getText(), c_password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(res -> {
            if (res.getKey().equals(res.getValue())) {
                // TODO update password
            }
        });
    }
}
