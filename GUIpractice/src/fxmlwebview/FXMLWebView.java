package fxmlwebview;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLWebView extends Application {

	@Override public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
		
		Scene scene = new Scene(root, 960, 720);
		
		stage.setScene(scene);
		stage.setTitle("FXML Web View");
		stage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
}



