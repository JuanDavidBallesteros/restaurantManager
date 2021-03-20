package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import model.RestaurantSystem;

import java.io.IOException;

public class ViewOrdersGUI {

    RestaurantSystem restaurantSystem = new RestaurantSystem();
    private RestaurantSystemGUI mainGUI;

    public ViewOrdersGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
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

        ObservableList<Order> contactObservableList = FXCollections.observableList(restaurantSystem.getOrders());

        tcID.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));
        tcClientName.setCellValueFactory(new PropertyValueFactory<Order, String>("client"));
        tcEmployeeName.setCellValueFactory(new PropertyValueFactory<Order, String>("employee"));
        tcDate.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
        tcActions.setCellValueFactory(new PropertyValueFactory<Order, Button>("actions"));

        tvTable.setItems(contactObservableList);

        tvTable.setRowFactory( tv -> {
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
