package ui;

import java.io.IOException;
import java.util.List;

import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewUsers {

    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    public ViewUsers(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
    }

    @FXML
    private TableColumn<Employee, String> tcEId;

    @FXML
    private TableColumn<Employee, String> tcEName;

    @FXML
    private TableColumn<User, String> tcUName;

    @FXML
    private TableColumn<User, String> tcCreated;

    @FXML
    private TableView<User> tvTable;

    public void initializeTableView() {

        ObservableList<User> ingredientObservableList = FXCollections.observableList(restaurantSystem.getUsers());

        tcUName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        tcEId.setCellValueFactory(new PropertyValueFactory<Employee, String>("idNumber"));
        tcEName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        tcCreated.setCellValueFactory(new PropertyValueFactory<User, String>("createdBy"));

        tvTable.setItems(ingredientObservableList);

        tvTable.setRowFactory( tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User temp = row.getItem();
                    try {
                        NewIngredientGUI controller = mainGUI.showNewIngredient(null);
                        //controller.fillForm(temp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

    }
}
