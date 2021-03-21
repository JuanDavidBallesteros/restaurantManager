package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Employee;
import model.Order;
import model.Person;
import model.RestaurantSystem;

import java.io.IOException;

public class NewEmployeeGUI {

    private RestaurantSystemGUI mainGUI;
    private RestaurantSystem restaurantSystem;

    public NewEmployeeGUI(RestaurantSystemGUI mainGUI) {
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
    void addEmployee(ActionEvent event) {
        restaurantSystem.addEmployee(txtName.getText(), txtLastName.getText(), txtID.getText());
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
