package ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class RestaurantSystemGUI {

    private Alert errorAlert;
    private Alert infoAlert;
    private Alert warningAlert;

    private RestaurantSystem restaurantSystem;

    public RestaurantSystem getRestaurantSystem() {
        return restaurantSystem;
    }

    public RestaurantSystemGUI() {
        restaurantSystem = new RestaurantSystem();
        this.errorAlert = new Alert(Alert.AlertType.ERROR);
        this.infoAlert = new Alert(Alert.AlertType.INFORMATION);
        this.warningAlert = new Alert(Alert.AlertType.WARNING);
    }

    @FXML
    private Label lblEmployee;

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
    public void initialize(URL arg0, ResourceBundle arg1) {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            if(currentTime.getMinute()<10 && currentTime.getSecond()<10){
                menuClock.setText(currentTime.getHour() + ":0" + currentTime.getMinute() + ":0" + currentTime.getSecond());
            } else if(currentTime.getMinute()<10) {
                menuClock.setText(currentTime.getHour() + ":0" + currentTime.getMinute() + ":" + currentTime.getSecond());
            } else if(currentTime.getSecond()<10) {
                menuClock.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":0" + currentTime.getSecond());
            } else {
                menuClock.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
            }
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    // AUTHENTICATION METHODS

    @FXML
    void logout(ActionEvent event) throws IOException {
        showLogin(null);
        restaurantSystem.setActualUser(null);
    }

    @FXML
    void login(ActionEvent event) {

    }

    // NAVIGATION METHODS

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

        if(restaurantSystem.getEmployees().isEmpty()) {
            lblEmployee.setDisable(true);
            cbEmployees.setDisable(true);
        } else {
            lblEmployee.setDisable(false);
            cbEmployees.setDisable(false);
        }

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

    // VERIFICATIONS

    @FXML
    void registerUser(ActionEvent event) {

        if(txtName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtID.getText().isEmpty() || txtRegUser.getText().isEmpty() || txtRegPassword.getText().isEmpty()) {

            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Empty Fields");
            errorAlert.setContentText("Please enter all the data.");
            errorAlert.showAndWait();

        } else {

            if(restaurantSystem.getEmployees().isEmpty()) {

                restaurantSystem.addFirstUser(txtName.getText(), txtLastName.getText(), txtID.getText(), txtRegUser.getText(), txtRegPassword.getText());

            } else {

                Employee employee = restaurantSystem.searchEmployee(cbEmployees.getValue());

                if(employee!=null) {
                    restaurantSystem.addUser(employee, txtRegUser.getText(), txtRegPassword.getText());
                }

            }

        }

    }

}

