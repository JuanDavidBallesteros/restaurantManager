package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.*;
import java.io.IOException;

public class NewClientGUI {

    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    public NewClientGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
    }

    @FXML
    private Label lblTitle;

    @FXML
    private Button btnCreate;

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
    private TextArea txtObservations;

    @FXML
    void addClient(ActionEvent event) {

    }

    @FXML
    void showClients(ActionEvent event) throws IOException {
        mainGUI.showClients(null);
    }

    public void fillForm(Client client) {
        lblTitle.setText("Actualizar Cliente");
        btnCreate.setText("Actualizar");
        txtName.setText(client.getName());
        txtLastName.setText(client.getLastName());
        txtID.setText(client.getIdNumber());
        txtAddress.setText(client.getAddress());
        txtPhone.setText(client.getPhone());
        txtObservations.setText(client.getObservations());
    }

}
