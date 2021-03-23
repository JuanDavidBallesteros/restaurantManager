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
import java.io.IOException;
import java.time.LocalTime;
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
            showAlert("ERROR", "Error", "Error en datos", "No se han podido cargar los datos");
            // e.printStackTrace();
        }
    }

    @FXML
    private Label lblActiveUser;

    @FXML
    private Label lblEmployee;

    @FXML
    private TextField txtClientSeparator;

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

    // INITIALIZE
    // ------------------------------------------------------------------------------------------------------

    @FXML
    public void initialize() {
        startClock();
    }

    // AUTHENTICATION METHODS
    // ------------------------------------------------------------------------------------------

    @FXML
    void logout(ActionEvent event) throws IOException {
        confirmAlert.setTitle("Cerrar Sesión");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("¿Está seguro que desea cerrar sesión?");
        confirmAlertOK.setText("Sí");
        confirmAlertCancel.setText("No");
        Optional<ButtonType> action = confirmAlert.showAndWait();

        if (action.get() == ButtonType.OK) {
            showLogin(null);
            restaurantSystem.setActualUser(null);
        }
    }

    @FXML
    void login(ActionEvent event) throws IOException {
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
            warningAlert.setTitle("Alerta");
            warningAlert.setHeaderText(null);
            warningAlert.setContentText("Nombre de usuario o contraseña incorrectos");
            warningAlert.showAndWait();
        }

    }

    // NAVIGATION
    // ------------------------------------------------------------------------------------------------------

    @FXML
    void showLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        fxmlLoader.setController(this);
        Parent pane = fxmlLoader.load();
        mainPane.getChildren().setAll(pane);
        menuBar.setDisable(true);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setWidth(1296);
    }

    @FXML
    void showRegister(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        fxmlLoader.setController(this);
        Parent pane = fxmlLoader.load();
        mainPane.getChildren().setAll(pane);
        menuBar.setDisable(true);

        if (restaurantSystem.getEmployees().isEmpty()) {
            lblEmployee.setDisable(true);
            cbEmployees.setDisable(true);
        } else {
            lblEmployee.setDisable(false);
            cbEmployees.setDisable(false);
        }

    }

    @FXML
    void showSetup(ActionEvent event) throws IOException {
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
    void showMainMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        fxmlLoader.setController(this);
        Parent pane = fxmlLoader.load();
        mainPane.getChildren().setAll(pane);
        menuBar.setDisable(false);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setWidth(1496);
        showOrders(null);
        lblActiveUser.setText("Usuario: " + restaurantSystem.getActualUser());
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
    void showOrders(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewOrders.fxml"));
        ViewOrdersGUI controller = new ViewOrdersGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.initializeTableView();
    }

    @FXML
    void showEmployees(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewEmployees.fxml"));
        ViewEmployeeGUI controller = new ViewEmployeeGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.initializeTableView();
    }

    @FXML
    void showClients(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewClients.fxml"));
        ViewClientsGUI controller = new ViewClientsGUI(this);
        fxmlLoader.setController(controller);
        Parent pane = fxmlLoader.load();
        paneHolder.getChildren().setAll(pane);
        controller.initializeTableView();
    }

    // VERIFICATIONS
    // ---------------------------------------------------------------------------------------------------

    @FXML
    void registerUser(ActionEvent event) {

        if (txtName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtID.getText().isEmpty()
                || txtRegUser.getText().isEmpty() || txtRegPassword.getText().isEmpty()) {

            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Campos Vacíos");
            errorAlert.setContentText("Llena todos los campos");
            errorAlert.showAndWait();

        } else {

            if (restaurantSystem.getEmployees().isEmpty()) {

                try {
                    restaurantSystem.addFirstUser(txtName.getText(), txtLastName.getText(), txtID.getText(),
                            txtRegUser.getText(), txtRegPassword.getText());

                    infoAlert.setTitle("App");
                    infoAlert.setHeaderText(null);
                    infoAlert.setContentText("Hecho");
                    infoAlert.showAndWait();

                } catch (IOException e) {
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Acción incompleta");
                    errorAlert.showAndWait();
                }

            } else {

                Employee employee = restaurantSystem.searchEmployee(cbEmployees.getValue());

                if (employee != null) {
                    try {

                        for (int i = 0; i < restaurantSystem.getUsers().size(); i++) {
                            if (restaurantSystem.getUsers().get(i).getUserName() == txtRegUser.getText()) {
                                warningAlert.setTitle("Alerta");
                                warningAlert.setHeaderText(null);
                                warningAlert.setContentText("Nombre de usuario ya está registrado");
                                warningAlert.showAndWait();
                            } else {
                                restaurantSystem.addUser(employee, txtRegUser.getText(), txtRegPassword.getText());

                                infoAlert.setTitle("App");
                                infoAlert.setHeaderText(null);
                                infoAlert.setContentText("Hecho");
                                infoAlert.showAndWait();
                            }
                        }

                    } catch (IOException e) {
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Acción incompleta");
                        errorAlert.showAndWait();
                    }
                }

            }

        }

    }

    // IMPORTS
    // ---------------------------------------------------------------------------------------------------------

    @FXML
    void importClients(ActionEvent event) throws IOException {

        if (txtClientSeparator.getText().isEmpty()) {

            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Separador vacío");
            errorAlert.setContentText("Por favor, ingrese el separador de datos del archivo a importar.");
            errorAlert.showAndWait();

        } else {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Archivo");
            File file = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

            if (file != null) {

                int count = restaurantSystem.importClients(file.getPath(), txtClientSeparator.getText());

                infoAlert.setTitle("Información");
                infoAlert.setHeaderText("Clientes importados");
                if (count == 1) {
                    infoAlert.setContentText(count + " cliente fue importado correctamente.");
                } else {
                    infoAlert.setContentText(count + " clientes fueron importados correctamente.");
                }
                infoAlert.showAndWait();

            }

        }

    }

    // EXTRA
    // -----------------------------------------------------------------------------------------------------------

    void startClock() {
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

    public Optional<ButtonType> showConfirmAlert(String title, String content, String ok, String cancel) {
        confirmAlert.setTitle(title);
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText(content);
        confirmAlertOK.setText(ok);
        confirmAlertCancel.setText(cancel);
        return confirmAlert.showAndWait();
    }

}
