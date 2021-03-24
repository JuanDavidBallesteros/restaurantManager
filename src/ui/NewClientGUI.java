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

        try {
            
            if (txtName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtID.getText().isEmpty()
                    || txtAddress.getText().isEmpty() || txtPhone.getText().isEmpty()) {

                mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vacíos",
                        "Asegúrese de rellenar los campos obligatorios marcados con (*).");

                        //System.out.println("Entrada vacia");
            } else if (restaurantSystem.addClient(txtName.getText(), txtLastName.getText(), txtID.getText(),
                    txtAddress.getText(), txtPhone.getText(), txtObservations.getText())) {
                mainGUI.showAlert("INFO", "Información", "Cliente agregado",
                        "Se ha agregado el cliente correctamente.");
                        mainGUI.showClients(null);

                        //System.out.println("Creado");
            } else {
                mainGUI.showAlert("WARNING", "Alerta", "Error al agregar", "El empleado existe");
                txtName.setText("");
                txtLastName.setText("");
                txtID.setText("");

                //System.out.println("Existe");
            }
        } catch (IOException e) {
            mainGUI.showAlert("ERROR", "Error", "Error al agregar", "Ha ocurrido un error al agregar el cliente.");
        }

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
