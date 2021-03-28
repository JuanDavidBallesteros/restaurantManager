package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewOrderGUI {

    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    private List<Product> productsSelected;
    private Client selectedClient;
    private Employee selectedEmployee;

    public NewOrderGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
        productsSelected = new ArrayList<>();
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
    private TableView<Client> tvClient;

    @FXML
    private TableColumn<Client, String> tcSearchClientName;

    @FXML
    private TableColumn<Client, String> tcSearchClientLastName;

    @FXML
    private TableColumn<Client, String> tcSearchClientId;

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
    private TableView<Employee> tvEmployees;

    @FXML
    private TableColumn<Employee, String> tcSearchEmployeeName;

    @FXML
    private TableColumn<Employee, String> tcSearchEmployeeLastName;

    @FXML
    private TableColumn<Employee, String> tcSearchEmployeeId;

    @FXML
    private Label lblClient;

    @FXML
    private Label lblEmployee;

    @FXML
    private TextField txtProduct;

    @FXML
    private TableView<Product> tvProducts;

    @FXML
    private TableColumn<Product, String> tcSearchProductName;

    @FXML
    private TableColumn<Product, String> tcSearchProductType;

    @FXML
    private TableColumn<Product, String> tcSearchProductSize;

    @FXML
    private TableColumn<Product, String> tcSearchProductCost;

    @FXML
    private TextField txtProductAmount;

    @FXML 
    private TableView<Product> tvOrder;

    @FXML
    private TableColumn<Product, String> tcOrderProduct;

    @FXML
    private TableColumn<Product, String> tcOrderType;

    @FXML
    private TableColumn<Product, String> tcOrderSize;

    @FXML
    private TableColumn<Product, String> tcOrderCost;

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
        if (btnToggleEdit.isSelected()) {
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

    public void initializeTableView() {

        // ---------------------------------- Client

        ObservableList<Client> clientsObservableList1 = FXCollections.observableList(restaurantSystem.getClients());

        tcSearchClientName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        tcSearchClientLastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        tcSearchClientId.setCellValueFactory(new PropertyValueFactory<Client, String>("idNumber"));

        tvClient.setItems(clientsObservableList1);

        tvClient.setRowFactory(tv -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Client temp = row.getItem();
                    selectedClient = temp;
                    initializeTableView();

                    
                }
            });
            return row;
        });

        // ---------------------------------- Employee

        ObservableList<Employee> employeesObservableList2 = FXCollections
                .observableList(restaurantSystem.getEmployees());

        tcSearchEmployeeName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        tcSearchEmployeeLastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        tcSearchEmployeeId.setCellValueFactory(new PropertyValueFactory<Employee, String>("idNumber"));

        tvEmployees.setItems(employeesObservableList2);

        tvEmployees.setRowFactory(tv -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Employee temp = row.getItem();
                    selectedEmployee = temp;
                    initializeTableView();
                }
            });
            return row;
        });

        // ---------------------------------- Product

        ObservableList<Product> productsObservableList2 = FXCollections
                .observableList(restaurantSystem.getDisplayProduct());

        tcSearchProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        tcSearchProductType.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));
        tcSearchProductCost.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        tcSearchProductSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));

        tvProducts.setItems(productsObservableList2);

        tvProducts.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product temp = row.getItem();
                    productsSelected.add(temp);
                    initializeTableView();
                }
            });
            return row;
        });

        // ---------------------------------- Order

        ObservableList<Product> orderObservableList2 = FXCollections
                .observableList(productsSelected);

                tcOrderProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
                tcOrderType.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));
                tcOrderSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
                tcOrderCost.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));

        tvOrder.setItems(orderObservableList2);

        tvOrder.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product temp = row.getItem();
                    productsSelected.remove(temp);
                    initializeTableView();
                }
            });
            return row;
        });

    }

}
