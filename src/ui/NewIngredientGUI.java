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
    private Label title;

    @FXML
    private ComboBox<String> comoBox;

    @FXML
    private Button addButton;

    private Ingredient ingredient;

    @FXML
    void add(ActionEvent event) throws IOException {
        if (addButton.getText().equals("Crear")) {
            try {
                if (txtName.getText().isEmpty() || comoBox.getSelectionModel().getSelectedItem().equals("Select")) {
                    mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vacíos",
                            "Asegúrese de rellenar los campos obligatorios marcados con (*).");
                } else {
                    if (restaurantSystem.addIngredient(txtName.getText(),
                            comoBox.getSelectionModel().getSelectedIndex())) {
                        mainGUI.showAlert("INFORMATION", "Información", "Ingrediente agregado",
                                "Se ha agregado el ingrediente correctamente.");

                        mainGUI.showIngredients(null);
                    } else {
                        mainGUI.showAlert("WARNING", "Alerta", "Error al agregar", "El ingrediente existe");
                    }
                }
            } catch (IOException e) {
                mainGUI.showAlert("ERROR", "Error", "Error al agregar",
                        "Ha ocurrido un error al agregar el ingrediente.");
            }
        } else {
            restaurantSystem.updateIngredient(ingredient, txtName.getText(),
                    comoBox.getSelectionModel().getSelectedIndex());
            mainGUI.showAlert("INFORMATION", "Información", "Ingrediente actualizado",
                    "Se ha actualizado el ingrediente correctamente.");

            mainGUI.showIngredients(null);
        }
    }

    @FXML
    void showLogin(ActionEvent event) throws IOException {
        mainGUI.showIngredients(null);
    }

    public void fillForm(Ingredient ingredient) {
        title.setText("Actualizar Ingrediente");
        addButton.setText("Actualizar");

        txtName.setText(ingredient.getName());
        comoBox.setValue(ingredient.getType());
        this.ingredient = ingredient;
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
