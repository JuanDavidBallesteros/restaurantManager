package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.*;

public class Main extends Application {

	private RestaurantSystem id;
	private AppMainGui appGUI;

	public Main() throws IOException {
		id = new RestaurantSystem();
		appGUI = new AppMainGui(id);

	}

	public static void main(String[] args) throws InterruptedException {

		RestaurantSystem rs = new RestaurantSystem();

		System.out.println("Loading App");

		// launch(args);
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
		// appGUI.showInitial();

	}
}

/*
 * 
 * import java.text.DateFormat; import java.text.SimpleDateFormat; import
 * java.time.LocalDateTime; import java.util.ArrayList; import java.util.Date;
 * import java.util.List;
 * 
 * 
 * Test objects rs.addFirstUser("Gerente", "1", "11110", "gerenciaRice",
 * "abc23"); User us1 = rs.getUsers().get(0); Employee em1 =
 * rs.getEmployees().get(0);
 * 
 * rs.setActualUser(us1);
 * 
 * rs.addClient("Cliente", "1", "12345", "address chocolate", 5555555,
 * "observations 122 1342"); Client cl1 = rs.getClients().get(0);
 * 
 * rs.addEmployee("Empleado", "1", "1111"); Employee em2 =
 * rs.getEmployees().get(1);
 * 
 * rs.addUser(rs.getEmployees().get(0), "ElEmpleado1", "abc123"); User us2 =
 * rs.getUsers().get(1);
 * 
 * rs.setActualUser(us2);
 * 
 * rs.addIngredient("cebolla", 1); rs.addIngredient("arroz", 1);
 * rs.addIngredient("queso", 1); rs.addIngredient("maduro", 1);
 * 
 * rs.addProduct("Arroz cebolla", 1, rs.getIngredients(), 1, (double)10000);
 * rs.addProduct("Arroz queso", 1, rs.getIngredients(), 1, (double)20000);
 * rs.addProduct("Arroz todo", 1, rs.getIngredients(), 1, (double)30000);
 * 
 * List<Integer> quan = new ArrayList<>(); quan.add(1); quan.add(2);
 * quan.add(3);
 * 
 * LocalDateTime.now(); DateFormat dateFormat = new
 * SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); Date date = new Date();
 * 
 * rs.addOrder(1, rs.getProducts(), quan, cl1.getName(), cl1.getLastName(),
 * em1.getIdNumber(), date, "observations order");
 */