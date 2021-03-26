package ui;

import model.*;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewIngredients {
    
    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    public ViewIngredients(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
    }

    @FXML
    private TableColumn<Ingredient, String> tcTypeName;

    @FXML
    private TableView<Ingredient> tvTable;

    @FXML
    private TableColumn<Ingredient, String> tcModifyBy;

    @FXML
    private TableColumn<Ingredient, String> tcIngredientName;

    @FXML
    private TableColumn<Ingredient, String> tcID;

    public void initializeTableView() {

        ObservableList<Ingredient> ingredientObservableList = FXCollections.observableList(restaurantSystem.getIngredients());

        tcID.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("id"));
        tcIngredientName.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
        tcTypeName.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("type"));
        tcModifyBy.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("modifiedBy"));

        tvTable.setItems(ingredientObservableList);

        tvTable.setRowFactory( tv -> {
            TableRow<Ingredient> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Ingredient temp = row.getItem();
                    try {
                        NewIngredientGUI controller = mainGUI.showNewIngredient(null);
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
