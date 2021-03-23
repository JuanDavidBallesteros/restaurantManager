package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.RestaurantSystem;

import java.io.IOException;

public class SetupGUI {

    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    public SetupGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
    }

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtRegUser;

    @FXML
    private PasswordField txtRegPassword;

    @FXML
    void showLogin(ActionEvent event) {
        if (txtName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtID.getText().isEmpty()
                || txtRegUser.getText().isEmpty() || txtRegPassword.getText().isEmpty()) {
            mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vacíos", "Asegúrese de rellenar los campos obligatorios marcados con (*).");
        } else {
            try {
                restaurantSystem.addFirstUser(txtName.getText(), txtLastName.getText(), txtID.getText(),
                        txtRegUser.getText(), txtRegPassword.getText());
                mainGUI.showAlert("INFORMATION", "Información", "Configuración completada", "Inicie sesión para empezar a usar el programa.");
                mainGUI.showLogin(null);
            } catch (IOException e) {
                mainGUI.showAlert("ERROR", "Error", "Error al crear", "Ha ocurrido un error al crear el usuario.");
            }
        }
    }
}
