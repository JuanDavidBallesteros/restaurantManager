package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.RestaurantSystem;

public class Main extends Application {

	private RestaurantSystem id;
	private AppMainGui appGUI;

	public Main() throws IOException {
        id = new RestaurantSystem();
        appGUI = new AppMainGui(id);
    }

	public static void main(String[] args) {
		System.out.println("Loading App");
		launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        fxml.setController(appGUI);

        Parent root = fxml.load();
        Scene scene = new Scene(root);

        mainStage.setTitle("Contact Manager");

        mainStage.setScene(scene);
        mainStage.show();
		//appGUI.showInitial();

	}
}
