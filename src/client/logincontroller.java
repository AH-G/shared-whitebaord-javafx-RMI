package client;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import server.whiteboardserverinterface;

public class logincontroller implements Initializable{
	public int whiteboardlistviewint=-1;
	public whiteboardserverinterface whiteboard;
	public whiteboardclientinterface client;
	public MainController controller;
	public int clientid;
    @FXML
    private Button connectbutton;
    @FXML
    private TextField usernamefield;
    
    @FXML
    private Button registerbutton;

    @FXML
    private ListView<Integer> whiteboardlist;
    
    @FXML
    private Button createbutton;
    

	public void setwhiteboardclass(whiteboardserverinterface whiteboard) {
		this.whiteboard= whiteboard;	
	}
	public void setmaincontroller (MainController maincontrollerinstance) {
		this.controller= maincontrollerinstance;	
	}
	
	@FXML
    void registeraction(ActionEvent event) {
		try {
			String username = usernamefield.getText();
			if(username != null) {
				this.client = new whiteboardclientimpl(controller);
				this.clientid = whiteboard.registeruser(username, this.client);
				controller.setid(this.clientid);
				controller.setusername(username);
				ArrayList<Integer> whiteboardintlist = whiteboard.getwhiteboard();
				whiteboardlist.getItems().clear();
				for (int item: whiteboardintlist) {
					whiteboardlist.getItems().add(item);
				}
			}
			else {
				Dialog<String> dialog = new Dialog<String>();
		      	dialog.setTitle("Error");
		      	ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		      	dialog.setContentText("Please enter username");
		      	dialog.getDialogPane().getButtonTypes().add(type);
		      	dialog.showAndWait();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
	@FXML
    void connect(ActionEvent event) {
		try {
			/*
			whiteboardlist.setOnMouseClicked(EventHandler -> {
				String listitemstring = whiteboardlist.getSelectionModel().getSelectedItem().toString();
				whiteboardlistviewint = Integer.parseInt(listitemstring);
			});
			*/
			if(whiteboardlistviewint>=0) {
				
				this.whiteboard.connectwhiteboard(whiteboardlistviewint, this.clientid);
				controller.setwhiteboardid(whiteboardlistviewint);
				WhiteboardClient guiclass = new WhiteboardClient(controller);
				Stage thisstage = (Stage) connectbutton.getScene().getWindow();
				thisstage.close();
				guiclass.init();
				Stage stage = new Stage();
	            guiclass.start(stage);
			}
			else {
				Dialog<String> dialog = new Dialog<String>();
		      	dialog.setTitle("Error");
		      	ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		      	dialog.setContentText("whiteboard selected is null");
		      	dialog.getDialogPane().getButtonTypes().add(type);
		      	dialog.showAndWait();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void createbuttonaction(ActionEvent event) {
    	
    	
		
		try {
			int whiteboardtempid = whiteboard.createwhiteboard(controller.getid());
			ArrayList<Integer> whiteboardintlist = whiteboard.getwhiteboard();
			whiteboardlist.getItems().add(whiteboardtempid);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		whiteboardlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				// TODO Auto-generated method stub
				whiteboardlistviewint=whiteboardlist.getSelectionModel().getSelectedItem();
			}
		});
	}
	
}
