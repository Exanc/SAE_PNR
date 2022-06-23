package controlleur;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import modele.traitement.SQLQuerys;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;

import java.util.Optional;

import vue.ERole;

/**
 * Controlleur de widget utilisateur
 */
public class DefaultUser {

    @FXML private ComboBox cbRole;
    @FXML private Label lUsername;
    @FXML private Button btSupprimer;

    private AdminUtilisateurs parent;

    public void initialize() {
        cbRole.setItems(FXCollections.observableArrayList(ERole.values()));
    }

    /**
     * Changer les valeurs afficher sur l'interface
     * @param username nom
     * @param role role dans la BDD
     */
    public void setUser(String username, ERole role, AdminUtilisateurs parent) {
        cbRole.setValue(role);
        lUsername.setText(username);
        if (parent != null) this.parent = parent;
        if (SQLQuerys.getCurrentUser().equals(username + "@localhost")) {
            cbRole.setDisable(true);
            btSupprimer.setDisable(true);
        }
    }
    
    /**
     * Gére la sélection d'un rôle
     */
    public void cbRoleAction() {
        SQLQuerys.executeSQL("REVOKE 'observer', 'field_man', 'administrator' FROM " + lUsername.getText() + "@'localhost';");
        SQLQuerys.executeSQL("GRANT " + ((ERole) cbRole.getValue()).getRole() + " to " + lUsername.getText() + "@localhost;");
        SQLQuerys.executeSQL("SET DEFAULT ROLE ALL TO " + lUsername.getText() + "@localhost;");
        SQLQuerys.executeSQL("FLUSH PRIVILEGES;");
        parent.updateList();
    } 


    /**
     * Bouton de suppression d'utilisateur
     */
    public void btSupprimerAction() {
        SQLQuerys.executeSQL("DROP USER " + lUsername.getText() + "@'localhost';");
        parent.updateList();
    }

    /*
     * Bouton de changement du mot de passe
     */
    public void btChangePassword () {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);

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

        Node confirmButton = dialog.getDialogPane().lookupButton(ButtonType.APPLY);
        confirmButton.setDisable(true);

        password.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(!password.getText().equals(c_password.getText()));
            confirmButton.setDisable(password.getText().isEmpty());
        });
        
        c_password.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(!password.getText().equals(c_password.getText()));
            confirmButton.setDisable(password.getText().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> password.requestFocus());
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.APPLY) {
                return new Pair<>(password.getText(), c_password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(res -> {
            if (res.getKey().equals(res.getValue())) {
                SQLQuerys.executeSQL("ALTER USER " + lUsername.getText() + "@localhost IDENTIFIED BY '" + res.getKey() + "';");
            }
        });
    }
}
