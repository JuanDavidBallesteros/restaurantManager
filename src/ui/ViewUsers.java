package ui;

import java.io.IOException;

import model.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ViewUsers {

    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    public ViewUsers(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
    }

    @FXML
    private TableColumn<User, String> tcEId;

    @FXML
    private TableColumn<User, String> tcEName;

    @FXML
    private TableColumn<User, String> tcELastName;

    @FXML
    private TableColumn<User, String> tcUName;

    @FXML
    private TableColumn<User, String> tcCreated;

    @FXML
    private TableView<User> tvTable;

    public void initializeTableView() {

        ObservableList<User> ingredientObservableList = FXCollections.observableList(restaurantSystem.getUsers());

        tcUName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        tcCreated.setCellValueFactory(new PropertyValueFactory<User, String>("createdBy"));

        tcEId.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<User, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().getEmployee().getIdNumber());
            }
        });

        tcEName.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<User, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().getEmployee().getName());
            }
        });

        tcELastName.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<User, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().getEmployee().getLastName());
            }
        });


        tvTable.setItems(ingredientObservableList);

        tvTable.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User temp = row.getItem();
                    try {
                        NewUserGUI controller = mainGUI.newUsers(null);
                        controller.fillForm(temp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

    }
}
