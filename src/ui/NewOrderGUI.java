package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Order;

import java.io.IOException;

public class NewOrderGUI {

    private RestaurantSystemGUI mainGUI;

    public NewOrderGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    @FXML
    private Button btnSearchProduct;

    @FXML
    private Button btnAddSelected;

    @FXML
    private Button btnDeleteSelected;

    @FXML
    private ToggleButton btnToggleEdit;

    @FXML
    private Label lblOrder;

    @FXML
    private Button btnSearchClient;

    @FXML
    private Button btnSearchEmployee;

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
    public void initialize() {
        btnToggleEdit.setVisible(false);
    }

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

    public void fillForm(Order order) {
        toggleEdit(null);
        btnToggleEdit.setVisible(true);
        txtName.setText(order.getClient().getName());
        txtLastName.setText(order.getClient().getLastName());
        txtID.setText(order.getClient().getIdNumber());
        txtAddress.setText(order.getClient().getAddress());
        txtPhone.setText(order.getClient().getPhone());
        txtObservations.setText(order.getClient().getObservations());
        lblClient.setText("Cliente: " + order.getClient().getName() + " " + order.getClient().getLastName());
        lblEmployee.setText("Empleado: " + order.getEmployee().getName());
        lblOrder.setText("Orden: " + order.getId());
    }

    @FXML
    void toggleEdit(ActionEvent event) {
        if(btnToggleEdit.isSelected()) {
            btnToggleEdit.setText("Editar: Si");
            txtProduct.setDisable(false);
            btnSearchProduct.setDisable(false);
            tvProducts.setDisable(false);
            btnAddSelected.setDisable(false);
            btnDeleteSelected.setDisable(false);
            txtProductAmount.setDisable(false);
            btnSearchClient.setDisable(false);
            txtClient.setDisable(false);
            lblSearchTime.setDisable(false);
            tvClient.setDisable(false);
            txtEmployee.setDisable(false);
            btnSearchEmployee.setDisable(false);
            tvEmployees.setDisable(false);
            txtName.setEditable(true);
            txtLastName.setEditable(true);
            txtID.setEditable(true);
            txtAddress.setEditable(true);
            txtPhone.setEditable(true);
            txtObservations.setEditable(true);
        } else {
            btnToggleEdit.setText("Editar: No");
            txtProduct.setDisable(true);
            btnSearchProduct.setDisable(true);
            tvProducts.setDisable(true);
            btnAddSelected.setDisable(true);
            btnDeleteSelected.setDisable(true);
            txtProductAmount.setDisable(true);
            btnSearchClient.setDisable(true);
            txtClient.setDisable(true);
            lblSearchTime.setDisable(true);
            tvClient.setDisable(true);
            txtEmployee.setDisable(true);
            btnSearchEmployee.setDisable(true);
            tvEmployees.setDisable(true);
            txtName.setEditable(false);
            txtLastName.setEditable(false);
            txtID.setEditable(false);
            txtAddress.setEditable(false);
            txtPhone.setEditable(false);
            txtObservations.setEditable(false);
        }

    }

    @FXML
    void showOrders(ActionEvent event) throws IOException {
        mainGUI.showOrders(null);
    }

}
