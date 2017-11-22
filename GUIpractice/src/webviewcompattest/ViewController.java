package webviewcompattest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class ViewController {
	
	@FXML private BorderPane root;
	
	@FXML private WebView webView;
	
	public void initialize(){
		//attempt to load home page
		try {
			webView.getEngine().load(new File("web/index.html").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e1) {
			System.err.println(e1.getMessage());
		}
		
		//register click handler for every Hyperlink; target address comes from properties Map
		getAllNodes(root).stream()
			.filter( (Node node) -> node instanceof Hyperlink)	//filter for Hyperlink-class objects
			.filter( (Node node) -> node.getProperties().containsKey("hyperlink-target"))
			.forEach( (Node node) -> {
				((Hyperlink)node).setOnAction( (ActionEvent e) -> {
					try {
						webView.getEngine().load(new File("web/index.html").toURI().toURL().toExternalForm());
					} catch (MalformedURLException e1) {
						System.err.println(e1.getMessage());
					}
				});
			});
	}
	
	
	
	//from https://stackoverflow.com/questions/24986776/how-do-i-get-all-nodes-in-a-scene-in-javafx
	private static ArrayList<Node> getAllNodes(Parent root) {
	    ArrayList<Node> nodes = new ArrayList<Node>();
	    addAllDescendents(root, nodes);
	    return nodes;
	}
	
	//from https://stackoverflow.com/questions/24986776/how-do-i-get-all-nodes-in-a-scene-in-javafx
	private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
	    for (Node node : parent.getChildrenUnmodifiable()) {
	        nodes.add(node);
	        if (node instanceof Parent)
	            addAllDescendents((Parent)node, nodes);
	    }
	}
}
