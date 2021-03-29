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

public class NewProductGUI {

    private final RestaurantSystem restaurantSystem;
    private final RestaurantSystemGUI mainGUI;

    private Product acutalProduct;

    private List<Ingredient> selectIngredientsList;

    public NewProductGUI(RestaurantSystemGUI mainGUI) {
        this.mainGUI = mainGUI;
        restaurantSystem = mainGUI.getRestaurantSystem();
        selectIngredientsList = new ArrayList<>();
    }

    @FXML
    private TextField txtName;

    @FXML
    private Label availableLabel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TableView<Ingredient> selectedIngredients;

    @FXML
    private TableColumn<Ingredient, String> aListType;

    @FXML
    private TableColumn<Ingredient, String> sListType;

    @FXML
    private Button addButton;

    @FXML
    private Button delete; ////////

    @FXML
    private Label title;

    @FXML
    private ComboBox<String> isActive;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TableColumn<Ingredient, String> aListId;

    @FXML
    private TableColumn<Ingredient, String> aListName;

    @FXML
    private ComboBox<String> size;

    @FXML
    private TableColumn<Ingredient, String> sListId;

    @FXML
    private TableColumn<Ingredient, String> sListName;

    @FXML
    private TableView<Ingredient> actualIngredients;

    @FXML
    void addProduct(ActionEvent event) {
        if (addButton.getText().equals("Actualizar")) {
            if (selectIngredientsList.isEmpty() || txtName.getText().isEmpty() || txtPrice.getText().isEmpty()
                    || size.getSelectionModel().getSelectedItem().equals("Select")
                    || type.getSelectionModel().getSelectedItem().equals("Select")) {

                mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vacíos",
                        "Asegúrese de rellenar los campos obligatorios marcados con (*).");
            } else {
                try {
                    restaurantSystem.updateProduct(acutalProduct, txtName.getText(),
                            size.getSelectionModel().getSelectedIndex(), selectIngredientsList,
                            size.getSelectionModel().getSelectedIndex(), Double.parseDouble(txtPrice.getText()));

                    mainGUI.showAlert("INFORMATION", "Información", "Producto actualizado",
                            "Se ha actualizado el Producto correctamente.");

                    validateComboBox();

                    mainGUI.showProducts(null);

                } catch (NumberFormatException | IOException e) {
                    mainGUI.showAlert("ERROR", "Error", "Error al actualizar",
                            "Ha ocurrido un error al actualizar el Producto.");
                }
            }

        } else {
            try {
                if (selectIngredientsList.isEmpty() || txtName.getText().isEmpty() || txtPrice.getText().isEmpty()
                        || size.getSelectionModel().getSelectedItem().equals("Select")
                        || type.getSelectionModel().getSelectedItem().equals("Select")) {

                    mainGUI.showAlert("ERROR", "Error", "Campos obligatorios vacíos",
                            "Asegúrese de rellenar los campos obligatorios marcados con (*).");
                } else {

                    restaurantSystem.addProduct(txtName.getText(), size.getSelectionModel().getSelectedIndex(),
                            selectIngredientsList, size.getSelectionModel().getSelectedIndex(),
                            Double.parseDouble(txtPrice.getText()));

                    mainGUI.showAlert("INFORMATION", "Información", "Producto agregado",
                            "Se ha agregado el Producto correctamente.");

                    mainGUI.showProducts(null);

                }
            } catch (NumberFormatException | IOException e) {
                mainGUI.showAlert("ERROR", "Error", "Error al agregar", "Ha ocurrido un error al agregar el Producto.");
            }
        }
    }

    @FXML
    void showLogin(ActionEvent event) throws IOException {
        mainGUI.showProducts(null);
    }

    @FXML
    void delete(ActionEvent event) {
        try {

            if (restaurantSystem.removeProduct(acutalProduct)) {
                mainGUI.showAlert("INFORMATION", "Eliminado", null, "Se eliminó el registro");
                mainGUI.showProducts(null);
            } else {
                mainGUI.showAlert("WARNING", "Alerta", null, "Producto esta enlazado a una orden");
            }

        } catch (IOException e) {
            mainGUI.showAlert("ERROR", "Error", null, "Ha ocurrido un error al eliminar.");
        }
    }

    // -------------------------- action in table

    public void initializeTableView() {

        ObservableList<Ingredient> ingredientObservableList = FXCollections
                .observableList(restaurantSystem.getDisplayIngredients());

        aListId.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("id"));
        aListName.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
        aListType.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("type"));

        actualIngredients.setItems(ingredientObservableList);

        actualIngredients.setRowFactory(tv -> {
            TableRow<Ingredient> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Ingredient temp = row.getItem();
                    selectIngredientsList.add(temp);
                    initializeTableView();
                }
            });
            return row;
        });

        ObservableList<Ingredient> ingredientObservableList2 = FXCollections.observableList(selectIngredientsList);

        sListId.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("id"));
        sListName.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
        sListType.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("type"));

        selectedIngredients.setItems(ingredientObservableList2);

        selectedIngredients.setRowFactory(tv -> {
            TableRow<Ingredient> row2 = new TableRow<>();
            row2.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row2.isEmpty())) {
                    Ingredient temp = row2.getItem();
                    selectIngredientsList.remove(temp);
                    initializeTableView();
                }
            });
            return row2;
        });
    }

    // -------------------------- Update

    public void fillForm(Product product) {

        delete.setVisible(true);

        isActive.setDisable(false);
        availableLabel.setDisable(false);

        title.setText("Actualizar Producto");
        addButton.setText("Actualizar");

        txtName.setText(product.getName());
        txtPrice.setText(product.getPrice() + "");

        selectIngredientsList = product.getIngredients();
        initializeTableView();

        size.setValue(product.getSize());
        type.setValue(product.getType());

        acutalProduct = product;
    }

    // ---------------------- Combox Inicialitation

    public void comboInitialization() {

        List<String> types1 = new ArrayList<>();
        int i = 0;

        do {
            if (ProductType.values()[i].name() != null) {
                types1.add(ProductType.values()[i].name());
                i++;
            }

        } while (i < ProductType.values().length);

        ObservableList<String> optionsComboBox1 = FXCollections.observableArrayList(types1);
        type.setValue("Select");
        type.setItems(optionsComboBox1);

        ///////////////////////////////////////////////////////

        List<String> types2 = new ArrayList<>();
        int j = 0;

        do {
            if (ProductSize.values()[j].name() != null) {
                types2.add(ProductSize.values()[j].name());
                j++;
            }

        } while (j < ProductSize.values().length);

        ObservableList<String> optionsComboBox2 = FXCollections.observableArrayList(types2);
        size.setValue("Select");
        size.setItems(optionsComboBox2);

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
            acutalProduct.setAvailable(true);
        } else {
            acutalProduct.setAvailable(false);
        }
    }
}
