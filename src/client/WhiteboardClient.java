package client;

import java.rmi.Naming;
import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.stage.Stage;
import server.whiteboardserverinterface;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class WhiteboardClient extends Application {
	
	public static Stage mainstage;
	MainController controller;
	public whiteboardserverinterface server;
	
	public WhiteboardClient(MainController controller) {
		this.controller = controller;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			mainstage = primaryStage;
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			loader.setController(this.controller);
	        Parent root = loader.load();
			
			Scene scene = new Scene(root);
			primaryStage.setTitle("Paint App");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public MainController getMainController () {
		return this.controller;
	}
	public void setserver(whiteboardserverinterface server) {
		this.server=server;
	}

}

