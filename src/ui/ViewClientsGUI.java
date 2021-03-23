package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import model.RestaurantSystem;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class ViewClientsGUI {

    private final RestaurantSystemGUI mainGUI;
    private final RestaurantSystem restaurantSystem;

    public ViewClientsGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
    }

    @FXML
    private TableView<Client> tvTable;

    @FXML
    private TableColumn<Client, String> tcName;

    @FXML
    private TableColumn<Client, String> tcLastName;

    @FXML
    private TableColumn<Client, String> tcID;

    @FXML
    private TableColumn<Client, String> tcAddress;

    @FXML
    private TableColumn<Client, String> tcPhone;

    public void initializeTableView() {

        System.out.println(restaurantSystem.getClients().size());

        ObservableList<Client> employeeObservableList = FXCollections.observableList(restaurantSystem.getClients());

        tcName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        tcID.setCellValueFactory(new PropertyValueFactory<Client, String>("idNumber"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));

        tvTable.setItems(employeeObservableList);

        tvTable.setRowFactory( tv -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Client client = row.getItem();
                    System.out.println(client.getName());
                    try {
                        NewClientGUI controller = mainGUI.showNewClient(null);
                        controller.fillForm(client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

    }

}
