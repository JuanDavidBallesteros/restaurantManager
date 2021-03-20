package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private RestaurantSystemGUI restaurantSystemGUI;

    public Main() {
        restaurantSystemGUI = new RestaurantSystemGUI();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("window.fxml"));
        fxmlLoader.setController(restaurantSystemGUI);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        restaurantSystemGUI.showLogin(null);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
