package ui;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NewIngredientGUI {

    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    public NewIngredientGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
    }

    @FXML
    private TextField txtName;

    @FXML
    private Label Title;

    @FXML
    private ComboBox<String> comoBox;

    @FXML
    void add(ActionEvent event) {
        try {
            if (txtName.getText().isEmpty() || comoBox.getSelectionModel().getSelectedItem().equals("Select")) {
                mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vacíos",
                        "Asegúrese de rellenar los campos obligatorios marcados con (*).");
            } else {
                restaurantSystem.addIngredient(txtName.getText(), comoBox.getSelectionModel().getSelectedIndex());
                mainGUI.showAlert("INFORMATION", "Información", "Ingrdiente agregado",
                        "Se ha agregado el ingrediente correctamente.");
            }
        } catch (IOException e) {
            mainGUI.showAlert("ERROR", "Error", "Error al agregar", "Ha ocurrido un error al agregar el ingrediente.");
        }
    }

    @FXML
    void showLogin(ActionEvent event) throws IOException {
        mainGUI.showIngredients(null);
    }

    public void fillForm(Ingredient ingredient) {
        txtName.setText(ingredient.getName());
        comoBox.setValue(ingredient.getType());
    }

    public void comboInitialization() {
        List<String> types = new ArrayList<>();
        int i = 0;

        do {
            if (IngredientType.values()[i].name() != null) {
                types.add(IngredientType.values()[i].name());
                i++;
            }

        } while (i < IngredientType.values().length);

        ObservableList<String> optionsComboBox = FXCollections.observableArrayList(types);
        comoBox.setValue("Select");
        comoBox.setItems(optionsComboBox);
    }
}
