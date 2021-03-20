package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewOrderGUI {

    @FXML
    private TextField txtClient;

    @FXML
    private Label lblSearchTime;

    @FXML
    private TableView<?> tvClient;

    @FXML
    private TableColumn<?, ?> tcSearchClientName;

    @FXML
    private TableColumn<?, ?> tcSearchClientLastName;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtObservations;

    @FXML
    private TextField txtEmployee;

    @FXML
    private TableView<?> tvEmployees;

    @FXML
    private TableColumn<?, ?> tcSearchEmployeeName;

    @FXML
    private TableColumn<?, ?> tcSearchEmployeeLastName;

    @FXML
    private Label lblClient;

    @FXML
    private Label lblEmployee;

    @FXML
    private TextField txtProduct;

    @FXML
    private TableView<?> tvProducts;

    @FXML
    private TableColumn<?, ?> tcSearchProduct;

    @FXML
    private TableColumn<?, ?> tcSearchCost;

    @FXML
    private TextField txtProductAmount;

    @FXML
    private TableView<?> tvOrder;

    @FXML
    private TableColumn<?, ?> tcOrderAmount;

    @FXML
    private TableColumn<?, ?> tcOrderProduct;

    @FXML
    private TableColumn<?, ?> tcOrderCost;

    @FXML
    private TextArea lblOrderObservations;

    @FXML
    private Label lblTotal;

    @FXML
    void addOrder(ActionEvent event) {

    }

    @FXML
    void addSelectedProduct(ActionEvent event) {

    }

    @FXML
    void deleteSelectedProduct(ActionEvent event) {

    }

    @FXML
    void searchClient(ActionEvent event) {

    }

    @FXML
    void searchEmployee(ActionEvent event) {

    }

    @FXML
    void searchProduct(ActionEvent event) {

    }

}

