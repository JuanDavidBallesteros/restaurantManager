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
    private Label title;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtPassword;

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void showLogin(ActionEvent event) throws IOException {
        mainGUI.showUsers(null);
    }

    public void initializeTableView() {

        ObservableList<Employee> ingredientObservableList = FXCollections
                .observableList(restaurantSystem.getEmployees());

                tcNameE.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
                tcIdE.setCellValueFactory(new PropertyValueFactory<Employee, String>("idNum"));

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
}
