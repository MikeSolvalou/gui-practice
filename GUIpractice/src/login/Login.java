package login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		
		Text scenetitle = new Text("Welcome");
		scenetitle.setId("welcome-text");
		grid.add(scenetitle, 0, 0, 2, 1);
		
		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);
		
		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);
		
		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);
		
		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		
		grid.setGridLinesVisible(true);
		
		Button btn = new Button("Sign in");
		grid.add(btn, 1, 4);
		GridPane.setHalignment(btn, HPos.RIGHT);
		
		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);
		actiontarget.setId("actiontarget");
		
		btn.setOnAction( (ActionEvent event) -> {
			actiontarget.setText("Sign in button pressed");
		});
		
		
		Scene scene = new Scene(grid, 300, 275);
		scene.getStylesheets().add(Login.class.getResource("login.css").toExternalForm());
		primaryStage.setScene(scene);
				
		primaryStage.setTitle("JavaFX Welcome");
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
