package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class NewUserGUI {

    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    private User actualUser;

    private Employee selectedEmployee;

    public NewUserGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
    }

    @FXML
    private TableColumn<Employee, String> tcNameE;

    @FXML
    private TableView<Employee> tv;

    @FXML
    private TextField txtName;

    @FXML
    private TableColumn<Employee, String> tcIdE;

    @FXML
    private TableColumn<Employee, String> tcLastNameE;

    @FXML
    private Label title;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    void add(ActionEvent event) {

        if (title.getText().equals("Actualizar Usuario")) {

            if (txtName.getText().isEmpty() || txtPassword.getText().isEmpty()
                        || txtEmployeeName.getText().isEmpty()) {
                    mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vacíos",
                            "Asegúrese de rellenar los campos obligatorios marcados con (*).");
                } else {
                    try {
                        restaurantSystem.updateUser(actualUser, selectedEmployee, txtName.getText(), txtPassword.getText());
                        mainGUI.showAlert("INFORMATION", "Información", "Actualizado",
                            "Se ha actualizado el usuario correctamente.");
                            mainGUI.showUsers(null);
                    } catch (IOException e) {
                        mainGUI.showAlert("ERROR", "Error", "Error al actualizar",
                            "Ha ocurrido un error al actualizar el usuario.");
                    }

                }

        } else {

            try {
                if (txtName.getText().isEmpty() || txtPassword.getText().isEmpty()
                        || txtEmployeeName.getText().isEmpty()) {
                    mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vacíos",
                            "Asegúrese de rellenar los campos obligatorios marcados con (*).");
                } else {

                    if (restaurantSystem.addUser(selectedEmployee, txtName.getText(), txtPassword.getText())) {
                        mainGUI.showAlert("INFORMATION", "Información", "Usuario agregado",
                                "Se ha agregado el Usuario correctamente.");

                        mainGUI.showUsers(null);
                    } else {
                        mainGUI.showAlert("WARNING", "Alerta", "Error al agregar", "El usuario existe");
                    }
                }

            } catch (IOException e) {
                mainGUI.showAlert("ERROR", "Error", "Error al agregar", "Ha ocurrido un error al agregar el Usuario.");
            }

        }
    }

    @FXML
    void showLogin(ActionEvent event) throws IOException {
        mainGUI.showUsers(null);
    }

    @FXML                               //////////////////////
    void delete(ActionEvent event) {
        try {
            
            restaurantSystem.removeUser(actualUser);
            mainGUI.showAlert("WARNING", "Alerta", "Eliminado", "Se eliminó el registro");
            mainGUI.showProducts(null);

        } catch (IOException e) {
            mainGUI.showAlert("ERROR", "Error", null, "Ha ocurrido un error al eliminar.");
        }
    }

    public void initializeTableView() {

        ObservableList<Employee> ingredientObservableList = FXCollections
                .observableList(restaurantSystem.getEmployees());

        tcNameE.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        tcLastNameE.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        tcIdE.setCellValueFactory(new PropertyValueFactory<Employee, String>("idNumber"));

        tv.setItems(ingredientObservableList);

        tv.setRowFactory(tv -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    selectedEmployee = row.getItem();
                    txtEmployeeName.setText(selectedEmployee.getName() + " " + selectedEmployee.getLastName());
                }
            });
            return row;
        });
    }

    // -------------------------- Update

    public void fillForm(User user) {
        title.setText("Actualizar Usuario");
        add.setText("Actualizar");

        delete.setVisible(true); 

        txtName.setText(user.getUserName());
        txtPassword.setText(user.getUserPassword());

        selectedEmployee = user.getEmployee();
        txtEmployeeName.setText(user.getEmployee().getName() + " " + user.getEmployee().getLastName());

        initializeTableView();

        actualUser = user;

    }

}
