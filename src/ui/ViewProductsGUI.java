package ui;

import model.*;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewProductsGUI {
    
    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    public ViewProductsGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
    }

    @FXML
    private TableColumn<Product, String> tcName;

    @FXML
    private TableColumn<Product, String> tcType;

    @FXML
    private TableColumn<Product, String> tcPrice;

    @FXML
    private TableView<Product> tvTable;

    @FXML
    private TableColumn<Product, String> tcMOdificated;

    @FXML
    private TableColumn<Product, String> tcID;

    @FXML
    private TableColumn<Product, String> tcSize;


    public void initializeTableView() {

        ObservableList<Product> ingredientObservableList = FXCollections.observableList(restaurantSystem.getDisplayProduct());

        tcID.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        tcType.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));
        tcSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        tcMOdificated.setCellValueFactory(new PropertyValueFactory<Product, String>("modifiedBy"));

        tvTable.setItems(ingredientObservableList);

        tvTable.setRowFactory( tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product temp = row.getItem();
                    try {
                        NewProductGUI controller = mainGUI.showNewProduct(null);
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
