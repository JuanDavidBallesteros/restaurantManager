package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.*;
import java.io.IOException;

public class NewEmployeeGUI {

    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    private Employee actualEmployee;

    public NewEmployeeGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
    }

    @FXML
    private Button add;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtID;

    @FXML
    void addEmployee(ActionEvent event) {
        if (add.getText().equals("Actualizar")) {
            if (txtName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtID.getText().isEmpty()) {
                mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vacíos",
                        "Asegúrese de rellenar los campos obligatorios marcados con (*).");
            } else {
                try {
                    restaurantSystem.updateEmployee(actualEmployee, txtName.getText(), txtLastName.getText(), txtID.getText());
                    mainGUI.showAlert("INFORMATION", "Información", "Ingrediente actualizado",
                        "Se ha actualizado el empleado correctamente.");
                } catch (IOException e) {
                    mainGUI.showAlert("ERROR", "ERROR", null, "No se pudo actualizar el registro.");
                }
            }

        } else {
            try {
                if (txtName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtID.getText().isEmpty()) {
                    mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vacíos",
                            "Asegúrese de rellenar los campos obligatorios marcados con (*).");
                } else {
                    restaurantSystem.addEmployee(txtName.getText(), txtLastName.getText(), txtID.getText());
                    mainGUI.showAlert("INFORMATION", "Información", "Empleado agregado",
                            "Se ha agregado el empleado correctamente.");
                    mainGUI.showEmployees(null);
                }
            } catch (IOException e) {
                mainGUI.showAlert("ERROR", "Error", "Error al agregar", "Ha ocurrido un error al agregar el empleado.");
            }
        }
    }

    @FXML
    void showLogin(ActionEvent event) throws IOException {
        mainGUI.showLogin(null);
    }

    public void fillForm(Employee employee) {

        add.setText("Actualizar");

        txtName.setText(employee.getName());
        txtLastName.setText(employee.getLastName());
        txtID.setText(employee.getIdNumber());

        actualEmployee = employee;
    }

}
