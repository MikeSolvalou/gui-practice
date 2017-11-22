package webviewcompattest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CompatTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
		
		Scene scene = new Scene(root, 1280, 720);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Compat Test Zone");
		primaryStage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
