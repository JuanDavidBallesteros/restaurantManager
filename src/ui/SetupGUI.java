package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    void showLogin(ActionEvent event) throws IOException {
        if(formIsEmpty()) {
            mainGUI.showAlert("Error", "Error", "Campos Vacíos", "Por favor, ingrese todos los datos.");
        } else {
            restaurantSystem.addFirstUser(txtName.getText(), txtLastName.getText(), txtID.getId(), txtRegUser.getText(), txtRegPassword.getText());
            mainGUI.showLogin(null);
        }

    }

    public boolean formIsEmpty() {
        return txtName.getText().isEmpty() || txtName.getText().isEmpty() || txtName.getText().isEmpty() || txtName.getText().isEmpty() || txtName.getText().isEmpty();
    }

}
