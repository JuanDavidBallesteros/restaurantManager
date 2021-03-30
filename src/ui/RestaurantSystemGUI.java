package ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

public class RestaurantSystemGUI {

    private final RestaurantSystem restaurantSystem;

    private final Alert errorAlert;
    private final Alert infoAlert;
    private final Alert warningAlert;
    private final Alert confirmAlert;
    private final Button confirmAlertOK;
    private final Button confirmAlertCancel;

    public RestaurantSystem getRestaurantSystem() {
        return restaurantSystem;
    }

    public RestaurantSystemGUI() {
        restaurantSystem = new RestaurantSystem();

        this.errorAlert = new Alert(AlertType.ERROR);
        this.infoAlert = new Alert(AlertType.INFORMATION);
        this.warningAlert = new Alert(AlertType.WARNING);
        this.confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlertOK = ((Button) confirmAlert.getDialogPane().lookupButton(ButtonType.OK));
        confirmAlertCancel = ((Button) confirmAlert.getDialogPane().lookupButton(ButtonType.CANCEL));

        try {
            restaurantSystem.loadData();
        } catch (ClassNotFoundException | IOException e) {
            showAlert("ERROR", "Error", "Error en datos", "No se han podido cargar los datos.");
            // e.printStackTrace();
        }
    }

    @FXML
    private DatePicker startDateOrder;

    @FXML
    private TextField startTimeOrder;

    @FXML
    private DatePicker endDateOrder;

    @FXML
    private TextField endTimeOrder;

    @FXML
    private Label lblActiveUser;

    @FXML
    private Label lblEmployee;

    @FXML
    private TextField txtClientSeparator;

    @FXML
    private TextField txtOrderSeparatorImp;

    @FXML
    private TextField txtOrderSeparatorExp;

    @FXML
    private TextField txtIngredientSeparator;

    @FXML
    private ChoiceBox<String> cbEmployees;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtRegUser;

    @FXML
    private PasswordField txtRegPassword;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Pane mainPane;

    @FXML
    private Pane paneHolder;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPassword;

    @FXML
    private Menu menuClock;

    @FXML
    private TextField txtProductsSeparatorEx;

    @FXML
    private TextField txtProductsSeparatorImp;

    @FXML
    private DatePicker startDateEmployee;

    @FXML
    private TextField startTimeEmployee;

    @FXML
    private DatePicker endDateEmployee;

    @FXML
    private TextField endTimeEmployee;

    @FXML
    private TextField txtEmployeeSeparatorExp;

    @FXML
    private DatePicker startDateProduct;

    @FXML
    private TextField startTimeProduct;

    @FXML
    private DatePicker endDateProduct;

    @FXML
    private TextField endTimeProduct;

    // INITIALIZE
    // ------------------------------------------------------------------------------------------------------

    @FXML
    public void initialize() {
        startClock();
    }

    // AUTHENTICATION METHODS
    // ------------------------------------------------------------------------------------------

    @FXML
    public void logout(ActionEvent event) throws IOException {
        if (showConfirmAlert("Cerrar Sesión", "¿Está seguro que desea cerrar sesión?", "Si", "No")) {
            showLogin(null);
            restaurantSystem.setActualUser(null);
        }
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        boolean access = false;

        for (int i = 0; i < restaurantSystem.getUsers().size() && !access; i++) {
            if (txtUser.getText().equals(restaurantSystem.getUsers().get(i).getUserName())) {
                if (txtPassword.getText().equals(restaurantSystem.getUsers().get(i).getUserPassword())) {
                    access = true;
                }
            }
        }

        if (access) {
            User actualUser = restaurantSystem.searchUser(txtUser.getText());
            restaurantSystem.setActualUser(actualUser);
            showMainMenu(null);
        } else {
            showAlert("ERROR", "Error", "Datos incorrectos", "El usuario no existe o la contraseña es incorrecta.");
            txtUser.setText("");
            txtPassword.setText("");
        }
    }

    // NAVIGATION
    // ------------------------------------------------------------------------------------------------------

    @FXML
    public void showLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        fxmlLoader.setController(this);
        Parent pane = fxmlLoader.load();
        mainPane.getChildren().setAll(pane);
        menuBar.setDisable(true);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setWidth(1296);
    }

    @FXML
    public void showSetup(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setup.fxml"));
        SetupGUI controller = new SetupGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        mainPane.getChildren().setAll(pane);
        menuBar.setDisable(true);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setWidth(1296);
    }

    @FXML
    public void showMainMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        fxmlLoader.setController(this);
        Parent pane = fxmlLoader.load();
        mainPane.getChildren().setAll(pane);
        menuBar.setDisable(false);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setWidth(1496);
        showOrders(null);
        lblActiveUser.setText("Usuario: " + restaurantSystem.getActualUser().getUserName());
    }

    @FXML
    public NewEmployeeGUI showNewEmployee(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newEmployee.fxml"));
        NewEmployeeGUI controller = new NewEmployeeGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        return controller;
    }

    @FXML
    public NewOrderGUI showNewOrder(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newOrder.fxml"));
        NewOrderGUI controller = new NewOrderGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.initializeTableView();
        controller.comboInitialization();
        return controller;
    }

    @FXML
    public NewClientGUI showNewClient(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newClient.fxml"));
        NewClientGUI controller = new NewClientGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        return controller;
    }

    @FXML
    public void showOrders(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewOrders.fxml"));
        ViewOrdersGUI controller = new ViewOrdersGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.initializeTableView();
    }

    @FXML
    public void showEmployees(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewEmployees.fxml"));
        ViewEmployeeGUI controller = new ViewEmployeeGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.initializeTableView();
    }

    @FXML
    public void showClients(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewClients.fxml"));
        ViewClientsGUI controller = new ViewClientsGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.initializeTableView();
    }

    @FXML
    public void showIngredients(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewIngredients.fxml"));
        ViewIngredients controller = new ViewIngredients(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.initializeTableView();
    }

    @FXML
    public NewIngredientGUI showNewIngredient(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newIngredient.fxml"));
        NewIngredientGUI controller = new NewIngredientGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.comboInitialization();

        return controller;
    }

    @FXML
    public void showProducts(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewProducts.fxml"));
        ViewProductsGUI controller = new ViewProductsGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.initializeTableView();
    }

    @FXML
    public NewProductGUI showNewProduct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newProduct.fxml"));
        NewProductGUI controller = new NewProductGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.initializeTableView();
        controller.comboInitialization();

        return controller;
    }

    @FXML
    public void showUsers(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewUsers.fxml"));
        ViewUsers controller = new ViewUsers(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.initializeTableView();
    }

    @FXML
    public NewUserGUI newUsers(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newUser.fxml"));
        NewUserGUI controller = new NewUserGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.initializeTableView();

        return controller;
    }

    // IMPORTS
    // ---------------------------------------------------------------------------------------------------------

    @FXML
    public void importClients(ActionEvent event) throws IOException {

        if (txtClientSeparator.getText().isEmpty()) {
            showAlert("ERROR", "Error", "Separador vacío", "Ingrese el separador de datos del archivo a importar.");
        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Archivo");
            File file = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

            if (file != null) {
                int count = restaurantSystem.importClients(file.getPath(), txtClientSeparator.getText());
                if (count == 1) {
                    showAlert("INFORMATION", "Información", "Cliente importado",
                            count + " cliente fue importado correctamente.");
                } else {
                    showAlert("INFORMATION", "Información", "Clientes importados",
                            count + " clientes fueron importados correctamente.");
                }
                showClients(null);
            }
        }
    }

    @FXML
    public void importIngredients(ActionEvent event) throws FileNotFoundException, IOException {
        if (txtIngredientSeparator.getText().isEmpty()) {
            showAlert("ERROR", "Error", "Separador vacío", "Ingrese el separador de datos del archivo a importar.");
        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Archivo");
            File file = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

            if (file != null) {
                int count = restaurantSystem.importIngredients(file.getPath(), txtIngredientSeparator.getText());

                showAlert("INFORMATION", "Información", "Clientes importados",
                        count + " clientes fueron importados correctamente.");

                showIngredients(null);
            }
        }
    }

    @FXML
    void importProducts(ActionEvent event) throws FileNotFoundException, IOException {
        if (txtProductsSeparatorImp.getText().isEmpty()) {
            showAlert("ERROR", "Error", "Separador vacío", "Ingrese el separador de datos del archivo a importar.");
        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Archivo");
            File file = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

            if (file != null) {
                int count = restaurantSystem.importProducts(file.getPath(), txtProductsSeparatorImp.getText());

                showAlert("INFORMATION", "Información", "Clientes importados",
                        count + " clientes fueron importados correctamente.");

                showProducts(null);
            }
        }
    }

    @FXML
    void importOrders(ActionEvent event) throws FileNotFoundException, IOException, ParseException {

        if(restaurantSystem.getClients().isEmpty() || restaurantSystem.getEmployees().isEmpty() || restaurantSystem.getIngredients().isEmpty() || restaurantSystem.getProducts().isEmpty()) {
            showAlert("WARNING", "Advertencia", "Faltan datos", "Recuerde que para importar ordenes, debe haber importado antes los clientes, empleados, productos e ingredientes correspondientes.");
        } else {
            if (txtOrderSeparatorImp.getText().isEmpty()) {
                showAlert("ERROR", "Error", "Separador vacío", "Ingrese el separador de datos del archivo a importar.");
            } else {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Seleccionar Archivo");
                File file = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

                if (file != null) {
                    int count;

                    count = restaurantSystem.importOrders(file.getPath(), txtOrderSeparatorImp.getText());
                    showAlert("INFORMATION", "Información", "Ordenes importadas",
                            count + " Ordenes fueron importados correctamente.");

                    showOrders(null);
                }
            }
        }
    }

    // EXPORTS ---------------------------------------------------------------------------------------------------------

    @FXML
    void exportOrders(ActionEvent event) throws ParseException, FileNotFoundException {

        if(restaurantSystem.getOrders().isEmpty()) {

            showAlert("ERROR", "Error", "Sin Datos", "No hay ordenes para exportar.");

        } else {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exportar Reporte");
            fileChooser.setInitialFileName("reporte-ordenes");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
            File file = fileChooser.showSaveDialog(mainPane.getScene().getWindow());

            if(file!=null) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                String startDateString = startDateOrder.getValue().toString();
                String startTimeString = startTimeOrder.getText()+":00";
                String startDateFinal = startDateString + " " + startTimeString;
                Date startDate = dateFormat.parse(startDateFinal);

                String endDateString = endDateOrder.getValue().toString();
                String endTimeString = endTimeOrder.getText()+":00";
                String endDateFinal = endDateString + " " + endTimeString;
                Date endDate = dateFormat.parse(endDateFinal);

                restaurantSystem.exportOrders(file, txtOrderSeparatorExp.getText(), endDate, startDate);

                showAlert("INFORMATION", "Información", "Ordenes exportadas", "Las ordenes fueron exportadas correctamente.");

            }

        }

    }

    @FXML
    void exportEmployees(ActionEvent event) throws ParseException, FileNotFoundException {

        if(restaurantSystem.getEmployees().isEmpty()) {

            showAlert("ERROR", "Error", "Sin Datos", "No hay empleados para exportar.");

        } else {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exportar Reporte");
            fileChooser.setInitialFileName("reporte-empleados");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
            File file = fileChooser.showSaveDialog(mainPane.getScene().getWindow());

            if(file!=null) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                String startDateString = startDateEmployee.getValue().toString();
                String startTimeString = startTimeEmployee.getText()+":00";
                String startDateFinal = startDateString + " " + startTimeString;
                Date startDate = dateFormat.parse(startDateFinal);

                String endDateString = endDateEmployee.getValue().toString();
                String endTimeString = endTimeEmployee.getText()+":00";
                String endDateFinal = endDateString + " " + endTimeString;
                Date endDate = dateFormat.parse(endDateFinal);

                restaurantSystem.exportEmployeesReports(file, txtOrderSeparatorExp.getText(), endDate, startDate);

                showAlert("INFORMATION", "Información", "Reporte Generado", "El reporte de los empleados fue generado correctamente.");

            }

        }

    }

    @FXML
    void exportProducts(ActionEvent event) throws ParseException, FileNotFoundException {

        if(restaurantSystem.getEmployees().isEmpty()) {

            showAlert("ERROR", "Error", "Sin Datos", "No hay empleados para exportar.");

        } else {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exportar Reporte");
            fileChooser.setInitialFileName("reporte-productos");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
            File file = fileChooser.showSaveDialog(mainPane.getScene().getWindow());

            if(file!=null) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                String startDateString = startDateProduct.getValue().toString();
                String startTimeString = startTimeProduct.getText()+":00";
                String startDateFinal = startDateString + " " + startTimeString;
                Date startDate = dateFormat.parse(startDateFinal);

                String endDateString = endDateProduct.getValue().toString();
                String endTimeString = endTimeProduct.getText()+":00";
                String endDateFinal = endDateString + " " + endTimeString;
                Date endDate = dateFormat.parse(endDateFinal);

                restaurantSystem.exportProductsReport(file, txtOrderSeparatorExp.getText(), endDate, startDate);

                showAlert("INFORMATION", "Información", "Reporte Generado", "El reporte de los productos fue generado correctamente.");

            }

        }

    }

    // EXTRA
    // -----------------------------------------------------------------------------------------------------------

    public void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            if (currentTime.getMinute() < 10 && currentTime.getSecond() < 10) {
                menuClock.setText(
                        currentTime.getHour() + ":0" + currentTime.getMinute() + ":0" + currentTime.getSecond());
            } else if (currentTime.getMinute() < 10) {
                menuClock.setText(
                        currentTime.getHour() + ":0" + currentTime.getMinute() + ":" + currentTime.getSecond());
            } else if (currentTime.getSecond() < 10) {
                menuClock.setText(
                        currentTime.getHour() + ":" + currentTime.getMinute() + ":0" + currentTime.getSecond());
            } else {
                menuClock
                        .setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
            }
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    void aboutUs(ActionEvent event) {
        showAlert("INFORMATION", "Información", "Restaurant System", "Este programa fue desarrollado por Juan David Ballesteros y Juan Felipe Sinisterra.");
    }

    // ALERTS
    // ----------------------------------------------------------------------------------------------------------

    public void showAlert(String type, String title, String header, String content) {
        switch (type) {
        case "ERROR":
            errorAlert.setTitle(title);
            errorAlert.setHeaderText(header);
            errorAlert.setContentText(content);
            errorAlert.showAndWait();
            break;
        case "INFORMATION":
            infoAlert.setTitle(title);
            infoAlert.setHeaderText(header);
            infoAlert.setContentText(content);
            infoAlert.showAndWait();
            break;
        case "WARNING":
            warningAlert.setTitle(title);
            warningAlert.setHeaderText(header);
            warningAlert.setContentText(content);
            warningAlert.showAndWait();
            break;
        }
    }

    public boolean showConfirmAlert(String title, String content, String ok, String cancel) {
        confirmAlert.setTitle(title);
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText(content);
        confirmAlertOK.setText(ok);
        confirmAlertCancel.setText(cancel);
        Optional<ButtonType> action = confirmAlert.showAndWait();

        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    // DATA
    // ------------------------------------------------------------------------------------------------------------

    @FXML
    public void deleteAllData(ActionEvent event) throws IOException {
        if (showConfirmAlert("Borrar Datos",
                "¿Está seguro que desea borrar todos los datos del programa? Esta acción no se podrá deshacer.", "Borrar",
                "Cancelar")) {
            restaurantSystem.getClients().clear();
            restaurantSystem.getIngredients().clear();
            restaurantSystem.setIngredientIdCount(0);
            restaurantSystem.getProducts().clear();
            restaurantSystem.setProductIdCount(0);
            restaurantSystem.getOrders().clear();
            restaurantSystem.setOrdersIdCount(0);
            restaurantSystem.saveData();
            showClients(null);
        }
    }

    @FXML
    void createTestEmployees(ActionEvent event) {

        Employee employee1 = new Employee("Juan", "Lopez", "0123456789", restaurantSystem.getActualUser().getUserName(), restaurantSystem.getActualUser().getUserName());
        restaurantSystem.getEmployees().add(employee1);
        Employee employee2 = new Employee("Felipe", "Soto", "9876543210", restaurantSystem.getActualUser().getUserName(), restaurantSystem.getActualUser().getUserName());
        restaurantSystem.getEmployees().add(employee2);

    }



}
