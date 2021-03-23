package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.*;
import java.io.IOException;

public class NewEmployeeGUI {

    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    private final Alert errorAlert;
    private final Alert infoAlert;
    private final Alert warningAlert;

    public NewEmployeeGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();

        this.errorAlert = new Alert(Alert.AlertType.ERROR);
        this.infoAlert = new Alert(Alert.AlertType.INFORMATION);
        this.warningAlert = new Alert(Alert.AlertType.WARNING);
    }

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtID;

    @FXML
    void addEmployee(ActionEvent event) {
        try {
            if(txtName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtID.getText().isEmpty() ){ 
                warningAlert.setTitle("Atención");
                warningAlert.setHeaderText(null);
                warningAlert.setContentText("Campos vacíos");
                warningAlert.showAndWait();
            } else { 
            restaurantSystem.addEmployee(txtName.getText(), txtLastName.getText(), txtID.getText());

            infoAlert.setTitle("App");
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("Hecho");
            infoAlert.showAndWait();

            mainGUI.showMainMenu(null);
            }
            

        } catch (IOException e) {
            
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Acción incompleta");
            errorAlert.showAndWait();
        }
    }

    @FXML
    void showLogin(ActionEvent event) throws IOException {
        mainGUI.showLogin(null);
    }

    public void fillForm(Employee employee) {
        txtName.setText(employee.getName());
        txtLastName.setText(employee.getLastName());
        txtID.setText(employee.getIdNumber());
    }

}
