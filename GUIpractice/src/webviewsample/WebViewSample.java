package webviewsample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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


class Browser extends Region {
	private HBox toolBar;
	
	final private static String[] imageFiles = new String[]{
			"product.png",
			"blog.png",
			"documentation.png",
			"partners.png"
	};
	
	final private static String[] captions = new String[]{
			"Products",
			"Blogs",
			"Documentation",
			"Partners"
	};
	
	final private static String[] urls = new String[]{
			"http://www.oracle.com/products/index.html",
			"http://blogs.oracle.com/",
			"http://docs.oracle.com/javase/index.html",
			"http://www.oracle.com/partners/index.html"
	};
	
	final Hyperlink[] hpls = new Hyperlink[captions.length];
	
	final WebView webView = new WebView();
	final WebEngine webEngine = webView.getEngine();
	
	
	public Browser() {
		//set style class
		getStyleClass().add("browser");
		
		//populate arrays
		for (int i = 0; i < captions.length; i++) {
			Image image = new Image(getClass().getResourceAsStream(imageFiles[i]));
			
			final Hyperlink hpl = hpls[i] = new Hyperlink(captions[i], new ImageView (image));
			
			final String url = urls[i];
			hpl.setOnAction( (ActionEvent e) -> {	//event handler for activating hyperlink
				webEngine.load(url);
			});
		}
		
		// load the home page
		webEngine.load("http://www.oracle.com/products/index.html");
		
		// create the toolbar
		toolBar = new HBox();
		toolBar.getStyleClass().add("browser-toolbar");
		toolBar.getChildren().addAll(hpls);
		
		//add components to this Browser object; layout handled by overriding Region.layoutChildren method 
		getChildren().add(toolBar);
		getChildren().add(webView);
	}
	
	@Override protected void layoutChildren() {	//for a Region, have to do this manually(?)
		double w = getWidth();
		double h = getHeight();
		double tbHeight = toolBar.prefHeight(w);
		layoutInArea(webView,0,0,w,h-tbHeight,0, HPos.CENTER, VPos.CENTER);
		layoutInArea(toolBar,0,h-tbHeight,w,tbHeight,0,HPos.CENTER,VPos.CENTER);
	}
}
