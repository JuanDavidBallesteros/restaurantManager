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

    private final Alert errorAlert;
    private final Alert infoAlert;
    private final Alert warningAlert;

    public SetupGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();

        this.errorAlert = new Alert(AlertType.ERROR);
        this.infoAlert = new Alert(AlertType.INFORMATION);
        this.warningAlert = new Alert(AlertType.WARNING);
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

            warningAlert.setTitle("Error");
            warningAlert.setHeaderText("Campos Vacíos");
            warningAlert.setContentText("Llena todos los campos");
            warningAlert.showAndWait();

        } else {
            try {
                restaurantSystem.addFirstUser(txtName.getText(), txtLastName.getText(), txtID.getText(),
                        txtRegUser.getText(), txtRegPassword.getText());

                infoAlert.setTitle("App");
                infoAlert.setHeaderText(null);
                infoAlert.setContentText("Hecho");
                infoAlert.showAndWait();

                mainGUI.showLogin(null);

            } catch (IOException e) {
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Acción incompleta");
                errorAlert.showAndWait();
            }
        }
    }

}
