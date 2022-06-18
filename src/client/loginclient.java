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


public class loginclient extends Application {
	
	public MainController maincontroller;
	public whiteboardserverinterface server;
	public logincontroller logincontrollerinstance;
	
	public loginclient(logincontroller controller) {
		// TODO Auto-generated constructor stub
		this.logincontrollerinstance = controller;
	}
		
	@Override
	public void start(Stage primaryStage) {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
			loader.setController(this.logincontrollerinstance);
	        Parent root = loader.load();
			
			Scene scene = new Scene(root);
			primaryStage.setTitle("Login Paint App");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public MainController getMainController () {
		return this.maincontroller;
	}
	public void setserver(whiteboardserverinterface server) {
		this.server=server;
	}

}

