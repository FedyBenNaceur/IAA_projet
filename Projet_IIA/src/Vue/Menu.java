package Vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("un test");
		AnchorPane myPane = (AnchorPane) FXMLLoader.load(getClass().getResource("Controler.fxml"));
		Scene myScene = new Scene(myPane, 865, 402);
		primaryStage.setScene(myScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}