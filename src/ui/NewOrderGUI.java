package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewOrderGUI {

    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    private List<Product> productsSelected;
    private Client selectedClient;
    private Employee selectedEmployee;
    private Order actualOrder;

    private ObservableList<Client> clientsObservableList;
    private ObservableList<Employee> employeesObservableList2;
    private ObservableList<Product> productsObservableList2;

    public NewOrderGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
        productsSelected = new ArrayList<>();

    }

    @FXML
    private ComboBox<String> comoBox;

    @FXML
    private Button delete;

    @FXML
    private Button btnSearchProduct;

    @FXML
    private Button btnAddSelected;

    @FXML
    private Button add;

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
    private Label lblEmployee;

    @FXML
    private Label lblClient;

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
        lblOrder.setText("#O" + restaurantSystem.getOrdersIdCount());
    }

    @FXML
    void addOrder(ActionEvent event) {
        if (add.getText().equals("Actualizar")) {
            if (comoBox.getSelectionModel().getSelectedItem().equals("Select") || productsSelected.isEmpty()
                    || selectedClient == null || selectedEmployee == null) {
                mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vac??os",
                        "Aseg??rese de rellenar los campos obligatorios marcados con (*).");
            } else {
                try {
                    restaurantSystem.updateOrder(actualOrder, comoBox.getSelectionModel().getSelectedIndex(),
                            productsSelected, selectedClient, selectedEmployee, actualDate(),
                            lblOrderObservations.getText());
                    mainGUI.showAlert("INFORMATION", "Informaci??n", "Orden actualizado",
                            "Se ha actualizado la orden correctamente.");
                } catch (IOException e) {
                    mainGUI.showAlert("ERROR", "Error", "Error al actualizar",
                            "Ha ocurrido un error al actualizar la orden.");

                }
            }
        } else {
            try {
                if (comoBox.getSelectionModel().getSelectedItem().equals("Select") || productsSelected.isEmpty()
                        || selectedClient == null || selectedEmployee == null) {
                    mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vac??os",
                            "Aseg??rese de rellenar los campos obligatorios marcados con (*).");

                } else {
                    restaurantSystem.addOrder(comoBox.getSelectionModel().getSelectedIndex(), productsSelected,
                            selectedClient, selectedEmployee, actualDate(), lblOrderObservations.getText());

                    mainGUI.showAlert("INFORMATION", "Informaci??n", "Orden agregada",
                            "Se ha agregado la orden correctamente.");

                    mainGUI.showOrders(null);

                }
            } catch (IOException e) {
                mainGUI.showAlert("ERROR", "Error", "Error al agregar", "Ha ocurrido un error al agregar la orden.");
            }
        }
    }

    @FXML
    void searchClient(ActionEvent event) {
        if (txtClient.getText().isEmpty()) {
            mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vac??os",
                    "Aseg??rese de rellenar los campos des b??squeda.");
        } else {

            long startTime = System.nanoTime();
            Client searchResult = restaurantSystem.searchClientByName(txtClient.getText());
            long endTime = System.nanoTime();
            double searchTime = (double)((endTime-startTime))/1000000;
            lblSearchTime.setText("Tiempo de b??squeda: " + searchTime + " milisegundos.");

            if (searchResult != null) {

                List<Client> tempList = new ArrayList<>();
                tempList.add(restaurantSystem.searchClientByName(txtClient.getText()));

                clientsObservableList = FXCollections.observableList(tempList);

                tcSearchClientName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
                tcSearchClientLastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
                tcSearchClientId.setCellValueFactory(new PropertyValueFactory<Client, String>("idNumber"));

                tvClient.setItems(clientsObservableList);

            } else {
                mainGUI.showAlert("WARNING", "Alerta", null, "No se encontr??");
            }
        }

    }

    @FXML
    void searchEmployee(ActionEvent event) {
        if (txtEmployee.getText().isEmpty()) {
            mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vac??os",
                    "Aseg??rese de rellenar los campos des b??squeda.");
        } else {

            if (restaurantSystem.searchEmployee(txtEmployee.getText()) != null) {

                List<Employee> tempList = new ArrayList<>();
                tempList.add(restaurantSystem.searchEmployee(txtEmployee.getText()));

                employeesObservableList2 = FXCollections.observableList(tempList);

                tcSearchEmployeeName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
                tcSearchEmployeeLastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
                tcSearchEmployeeId.setCellValueFactory(new PropertyValueFactory<Employee, String>("idNumber"));

                tvEmployees.setItems(employeesObservableList2);

            } else {
                mainGUI.showAlert("WARNING", "Alerta", null, "No se encontr??");
            }
        }
    }

    @FXML
    void searchProduct(ActionEvent event) {
        if (txtProduct.getText().isEmpty()) {
            mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vac??os",
                    "Aseg??rese de rellenar los campos des b??squeda.");
        } else {

            if (restaurantSystem.searchProduct(txtProduct.getText()) != null) {

                List<Product> tempList = new ArrayList<>();
                tempList.add(restaurantSystem.searchProduct(txtProduct.getText()));

                productsObservableList2 = FXCollections.observableList(tempList);

                tcSearchProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
                tcSearchProductType.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));
                tcSearchProductCost.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
                tcSearchProductSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));

                tvProducts.setItems(productsObservableList2);

            } else {
                mainGUI.showAlert("WARNING", "Alerta", null, "No se encontr??");
            }
        }
    }

    public void fillForm(Order order) {
        txtName.setText(order.getClient().getName());
        txtLastName.setText(order.getClient().getLastName());
        txtID.setText(order.getClient().getIdNumber());
        txtAddress.setText(order.getClient().getAddress());
        txtPhone.setText(order.getClient().getPhone());
        txtObservations.setText(order.getClient().getObservations());
        lblClient.setText("Cliente: " + order.getClient().getName() + " " + order.getClient().getLastName());
        add.setText("Actualizar");
        lblEmployee.setText("Empleado: " + order.getEmployee().getName() + " " + order.getEmployee().getLastName());
        lblOrder.setText("Orden: " + order.getId());

        comoBox.setValue(order.getState());

        productsSelected = order.getProducts();

        delete.setVisible(true); 

        actualOrder = order;

        initializeTableView();
    }

    @FXML
    void delete(ActionEvent event) {
        try {
            
            restaurantSystem.removeOrder(actualOrder);
            mainGUI.showAlert("WARNING", "Alerta", "Eliminado", "Se elimin?? el registro");
            mainGUI.showProducts(null);

        } catch (IOException e) {
            mainGUI.showAlert("ERROR", "Error", null, "Ha ocurrido un error al eliminar.");
        }
    }

    @FXML
    void showOrders(ActionEvent event) throws IOException {
        mainGUI.showOrders(null);
    }

    public void initializeTableView() {

        // ---------------------------------- Client

        clientsObservableList = FXCollections.observableList(restaurantSystem.getClients());

        tcSearchClientName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        tcSearchClientLastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        tcSearchClientId.setCellValueFactory(new PropertyValueFactory<Client, String>("idNumber"));

        tvClient.setItems(clientsObservableList);

        tvClient.setRowFactory(tv -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Client temp = row.getItem();
                    selectedClient = temp;
                    initializeTableView();

                    txtClient.setText(selectedClient.getFullName());
                    txtName.setText(selectedClient.getName());
                    txtLastName.setText(selectedClient.getLastName());
                    txtID.setText(selectedClient.getIdNumber());
                    txtAddress.setText(selectedClient.getAddress());
                    txtPhone.setText(selectedClient.getPhone());
                    txtObservations.setText(selectedClient.getObservations());
                }
            });
            return row;
        });

        // ---------------------------------- Employee

        employeesObservableList2 = FXCollections.observableList(restaurantSystem.getEmployees());

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
                    txtEmployee.setText(temp.getIdNumber());
                    initializeTableView();
                }
            });
            return row;
        });

        // ---------------------------------- Product

        productsObservableList2 = FXCollections.observableList(restaurantSystem.getDisplayProduct());

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
                    txtProduct.setText(temp.getName());
                    initializeTableView();
                    calculateAmount();
                }
            });
            return row;
        });

        // ---------------------------------- Order

        ObservableList<Product> orderObservableList2 = FXCollections.observableList(productsSelected);

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
                    calculateAmount();
                }
            });
            return row;
        });

    }

    public Double calculateAmount() {
        Double amount = (double) 0;

        for (int i = 0; i < productsSelected.size(); i++) {
            amount = amount + productsSelected.get(i).getPrice();
        }

        lblTotal.setText("Total: $" + amount);

        return amount;
    }

    public Date actualDate() {
        return new Date();
    }

    public void comboInitialization() {

        List<String> types = new ArrayList<>();
        int i = 0;

        do {
            if (OrderState.values()[i].name() != null) {
                types.add(OrderState.values()[i].name());
                i++;
            }

        } while (i < OrderState.values().length);

        ObservableList<String> optionsComboBox = FXCollections.observableArrayList(types);
        comoBox.setValue("Select");
        comoBox.setItems(optionsComboBox);
    }

}
