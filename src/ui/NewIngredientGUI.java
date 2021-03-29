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
    private Button delete;

    @FXML
    private Label availableLabel;

    @FXML
    private ComboBox<String> isActive;

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

            validateComboBox();

            mainGUI.showIngredients(null);
        }
    }

    @FXML
    void showLogin(ActionEvent event) throws IOException {
        mainGUI.showIngredients(null);
    }

    @FXML //////////////////////
    void delete(ActionEvent event) {
        try {

            if (restaurantSystem.removeIngredient(ingredient)) {
                mainGUI.showAlert("INFORMATION", "Eliminado", null, "Se eliminó el registro");
                mainGUI.showProducts(null);
            } else {
                mainGUI.showAlert("WARNING", "Alerta", null, "Ingrediente esta enlazado a un producto");
            }

        } catch (IOException e) {
            mainGUI.showAlert("ERROR", "Error", null, "Ha ocurrido un error al eliminar.");
        }
    }

    public void fillForm(Ingredient ingredient) {
        delete.setVisible(true);

        isActive.setDisable(false);
        availableLabel.setDisable(false);

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

        ///////////////////////////////////////////////////////

        List<String> types3 = new ArrayList<>();

        types3.add("Activo");
        types3.add("Inactivo");

        ObservableList<String> optionsComboBox3 = FXCollections.observableArrayList(types3);
        isActive.setValue("Activo");
        isActive.setItems(optionsComboBox3);
    }

    private void validateComboBox() {
        if (isActive.getSelectionModel().getSelectedItem().equals("Activo")) {
            ingredient.setAvailable(true);
        } else {
            ingredient.setAvailable(false);
        }
    }
}
