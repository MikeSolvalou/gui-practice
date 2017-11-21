package webviewsample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WebViewSample extends Application {

	@Override public void start(Stage stage) {
		Scene scene = new Scene(new Browser(),900,600, Color.web("#666970"));
		scene.getStylesheets().add("webviewsample/BrowserToolbar.css");
		
		stage.setScene(scene);
		stage.setTitle("Web View");
		stage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
}
