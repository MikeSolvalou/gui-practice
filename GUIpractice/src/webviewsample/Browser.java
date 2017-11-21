package webviewsample;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import netscape.javascript.JSObject;


class Browser extends Region {
	private HBox toolBar;
	
	final private static String[] imageFiles = new String[]{
			"product.png",
			"blog.png",
			"documentation.png",
			"partners.png",
			"help.png"
	};
	
	final private static String[] captions = new String[]{
			"Products",
			"Blogs",
			"Documentation",
			"Partners",
			"Help"
	};
	
	final private static String[] urls = new String[]{
			"http://www.oracle.com/products/index.html",
			"http://blogs.oracle.com/",
			"http://docs.oracle.com/javase/index.html",
			"http://www.oracle.com/partners/index.html",
			Browser.class.getResource("help.html").toExternalForm()		//looks for help.html in same folder as Browser.java ?
	};
	
	final Hyperlink[] hpls = new Hyperlink[captions.length];
	
	final WebView webView = new WebView();
	final WebEngine webEngine = webView.getEngine();
	
	final Button toggleHelpTopics = new Button("Toggle Help Topics");
	private boolean needDocumentationButton = false;	//flag indicating whether the currently loaded webpage should be accompanied with the 'help topics' button
	
	
	public Browser() {
		//set style class
		getStyleClass().add("browser");
		
		//populate hpls array
		for (int i = 0; i < captions.length; i++) {
			hpls[i] = new Hyperlink(captions[i]);
			
			Image image = new Image(getClass().getResourceAsStream(imageFiles[i]));
			hpls[i].setGraphic(new ImageView(image));
			
			final boolean addButton = (hpls[i].getText().equals("Help"));
			
			final String url = urls[i];
			hpls[i].setOnAction( (ActionEvent e) -> {	//event handler for activating hyperlink
				needDocumentationButton = addButton;
				webEngine.load(url);
			});
		}
		
		//fill the toolbar
		toolBar = new HBox();
		toolBar.getStyleClass().add("browser-toolbar");
		toolBar.getChildren().addAll(hpls);
		
		//set action for the button
		toggleHelpTopics.setOnAction((ActionEvent t) -> {
			webEngine.executeScript("toggle_visibility('help_topics')");	//executes as if you typed this in the browser console?
		});
		
		//add listener that triggers whenever page loading state changes
		// page becomes visible several seconds before state becomes SUCCEEDED
		webEngine.getLoadWorker().stateProperty().addListener(
				(ObservableValue<? extends State> ov, State oldState, State newState) -> {
					toolBar.getChildren().remove(toggleHelpTopics);
					if (newState == State.SUCCEEDED) {
						JSObject win = (JSObject) webEngine.executeScript("window");	//gets (pointer to?) js object named 'window'; becomes JSObject-class object in java
						win.setMember("app", new JavaApp());							//adds member to window object named 'app'; app.exit() in js calls the JavaApp.exit() method from java
						
						if (needDocumentationButton) {
							toolBar.getChildren().add(toggleHelpTopics);
						}
					}
				});
		
		// load the home page
		webEngine.load("http://www.oracle.com/products/index.html");
		
		//add components to this Browser object; layout handled by overriding Region.layoutChildren method 
		getChildren().add(toolBar);
		getChildren().add(webView);
	}
	
	
	// js interface object
	public class JavaApp{
		public void exit(){
			Platform.exit();	//exits javafx application
		}
	}
	
	@Override protected void layoutChildren() {	//for a Region, have to do this manually(?)
		double w = getWidth();
		double h = getHeight();
		double tbHeight = toolBar.prefHeight(w);
		layoutInArea(webView,0,0,w,h-tbHeight,0, HPos.CENTER, VPos.CENTER);
		layoutInArea(toolBar,0,h-tbHeight,w,tbHeight,0,HPos.CENTER,VPos.CENTER);
	}
}