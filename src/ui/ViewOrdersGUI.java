package ui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.*;
import java.io.IOException;

public class ViewOrdersGUI {

    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    public ViewOrdersGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
    }

    @FXML
    private TableView<Order> tvTable;

    @FXML
    private TableColumn<Order, String> tcID;

    @FXML
    private TableColumn<Order, String> tcClientName;

    @FXML
    private TableColumn<Order, String> tcEmployeeName;

    @FXML
    private TableColumn<Order, String> tcDate;

    @FXML
    private TableColumn<Order, String> tcStatus;

    @FXML
    private TableColumn<Order, Button> tcActions;

    public void initializeTableView() {

        ObservableList<Order> orderObservableList = FXCollections.observableList(restaurantSystem.getOrders());

        tcID.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));       
        tcStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("state"));
        tcDate.setCellValueFactory(new PropertyValueFactory<Order, String>("dateTxt"));

        tcClientName.setCellValueFactory(new Callback<CellDataFeatures<Order, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Order, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().getClient().getFullName());
            }
        });

        tcEmployeeName.setCellValueFactory(new Callback<CellDataFeatures<Order, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Order, String> data) {
                return new ReadOnlyStringWrapper(
                        data.getValue().getEmployee().getName() + " " + data.getValue().getEmployee().getLastName());
            }
        });

        tvTable.setItems(orderObservableList);

        tvTable.setRowFactory(tv -> {
            TableRow<Order> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Order order = row.getItem();
                    try {
                        NewOrderGUI controller = mainGUI.showNewOrder(null);
                        controller.fillForm(order);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

    }

}
