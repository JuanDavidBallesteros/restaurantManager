package ui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewOrdersGUI {

    @FXML
    private TableView<?> tvTable;

    @FXML
    private TableColumn<?, ?> tcID;

    @FXML
    private TableColumn<?, ?> tcClientName;

    @FXML
    private TableColumn<?, ?> tcEmployeeName;

    @FXML
    private TableColumn<?, ?> tcDate;

    @FXML
    private TableColumn<?, ?> tcStatus;

    @FXML
    private TableColumn<?, ?> tcActions;

}
