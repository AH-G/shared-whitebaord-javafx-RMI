package client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class canvastextcontroller implements Initializable{
	
	MainController parentController;
    @FXML
    private TextField canvastext;

    @FXML
    private Button okay;
    
    @FXML
    void okayclosed(ActionEvent event) {
    	Stage stage = (Stage) okay.getScene().getWindow();
        // do what you have to do
    	String data = canvastext.getText();
    	if (data != null) {
    		parentController.gettextfromcanvascontroller(data);
            stage.close();
    	}
    	else {
    		Dialog<String> dialog = new Dialog<String>();
	      	dialog.setTitle("Error");
	      	ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
	      	dialog.setContentText("Please enter some text");
	      	dialog.getDialogPane().getButtonTypes().add(type);
	      	dialog.showAndWait();
    	}
    }
    
    void setparentcontroller(MainController objcontroller) {
    	this.parentController=objcontroller;
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
