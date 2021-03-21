package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.*;

import java.io.IOException;

public class ViewEmployeeGUI {

    private RestaurantSystemGUI mainGUI;
    private RestaurantSystem restaurantSystem;

    public ViewEmployeeGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
    }

    @FXML
    private TableView<Employee> tvTable;

    @FXML
    private TableColumn<Employee, String> tcName;

    @FXML
    private TableColumn<Employee, String> tcLastName;

    @FXML
    private TableColumn<Employee, String> tcID;

    @FXML
    private TableColumn<Employee, String> tcCreator;

    public void initializeTableView() {

        ObservableList<Employee> employeeObservableList = FXCollections.observableList(restaurantSystem.getEmployees());
        System.out.println(restaurantSystem.getEmployees().size());

        tcName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        tcID.setCellValueFactory(new PropertyValueFactory<Employee, String>("idNumber"));
        tcCreator.setCellValueFactory(new PropertyValueFactory<Employee, String>("createdBy"));

        tvTable.setItems(employeeObservableList);

        tvTable.setRowFactory( tv -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Employee employee = row.getItem();
                    try {
                        NewEmployeeGUI controller = mainGUI.showNewEmployee(null);
                        controller.fillForm(employee);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

    }


}

