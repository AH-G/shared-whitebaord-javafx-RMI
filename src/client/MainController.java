package client;


import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

//import javax.management.modelmbean.ModelMBean;
//import javax.naming.AuthenticationException;
import shapes.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import server.whiteboardserverinterface;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class MainController implements Initializable {
	public FreeLine freeLine;
	public StraightLine straightline;
	public Rectangleshape newrectangle;
	public Circleshape newcircle;
	public Triangleshape newtriangle;
	public whiteboardserverinterface whiteboard;
	public String canvastext;
	public int whiteboardid;
	public String usernameString;
	public int id;
	
    @FXML
    private Label whiteboard_connection_label;
    
    @FXML
    private Label username_label;

    @FXML
    private Label userstatus_label;
	
	@FXML
	private ColorPicker colorpicker;
	
	@FXML
	private TextField bsize;
	
	@FXML
	private TextField chaTextField;
	
	@FXML
	private TextField chattextfield;
	
	@FXML
	private TextArea chattextarea;
	
	@FXML
	private TextArea usersonline;
	
	@FXML TextField usertoklck;
	
	@FXML
    private ListView<String> chatlistview;
	
	@FXML
	private Canvas canvas;
	
	//@FXML
	//private MenuBar paintmenu = new MenuBar();
	
	
	boolean toolSelected = false;
	
	
	GraphicsContext brushTool;
    //rect2.setFill(Color.TRANSPARENT);
	
    //rect2.setStroke(Color.RED);
    //rect2.setStrokeWidth(10);
	
	private String mode;
	
	public void setwhiteboardclass(whiteboardserverinterface whiteboard) {
		this.whiteboard= whiteboard;
		
	}
	public void setid(int id) {
		this.id = id;
		
	}
	public void setusername(String username) {
		this.usernameString=username;
	}
	public void setwhiteboardid(int id) {
		this.whiteboardid=id;
	}
	public int getid() {
		return this.id;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	   
	    whiteboard_connection_label.setText(Integer.toString(this.whiteboardid));
	    username_label.setText(this.usernameString);
	    try {
			ArrayList<String> tempArrayList = this.whiteboard.getcollaboratoronline(whiteboardid);
			usersonline.setText("");
			for (int i=0; i<tempArrayList.size();i++) {
				usersonline.appendText("\n"+tempArrayList.get(i));
			}
			int manager = this.whiteboard.getmanager(whiteboardid);
			if (manager ==this.id) {
				userstatus_label.setText("Manager");
			}
			else {
				userstatus_label.setText("Collaborator");
			}
			this.whiteboard.gethistory(this.whiteboardid, this.id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    whiteboard_connection_label.setTextFill(Color.GREEN);
	}
	
	
	public void putmessage(String messgae) {
		this.chattextarea.appendText("\n"+messgae);
	}
	public void updateusersonline() throws RemoteException {
		ArrayList<String> tempArrayList;
		try {
			tempArrayList = this.whiteboard.getcollaboratoronline(whiteboardid);
			usersonline.setText("");
			for (int i=0; i<tempArrayList.size();i++) {
				usersonline.appendText("\n"+tempArrayList.get(i));
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
    void kickuserbuttonaction(ActionEvent event) {
		if(userstatus_label.getText().equals("Manager")) {
			if(!usertoklck.getText().equals("")) {
				int tempString= Integer.parseInt(usertoklck.getText());
				usertoklck.clear();
				if(tempString == this.id)
				{
					Dialog<String> dialog = new Dialog<String>();
			      	dialog.setTitle("Error");
			      	ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
			      	dialog.setContentText("You cannot kick yourself out");
			      	dialog.getDialogPane().getButtonTypes().add(type);
			      	dialog.showAndWait();
				}
				else {
					try {
						this.whiteboard.kickuser(this.whiteboardid, tempString);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else {
				Dialog<String> dialog = new Dialog<String>();
		      	dialog.setTitle("Error");
		      	ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		      	dialog.setContentText("Please write the userID in the field first to kick user");
		      	dialog.getDialogPane().getButtonTypes().add(type);
		      	dialog.showAndWait();
			}
		}
		else {
			Dialog<String> dialog = new Dialog<String>();
	      	dialog.setTitle("Error");
	      	ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
	      	dialog.setContentText("You are not manager so you are not authorized to perform this action");
	      	dialog.getDialogPane().getButtonTypes().add(type);
	      	dialog.showAndWait();
		}
		
		
    }
	public void other_draw(String othermode, String method, double x, double y, String othercolor) {
		brushTool=canvas.getGraphicsContext2D();
		brushTool.setStroke(Color.web(othercolor));
		
		if(othermode.equals("free")) {
			if(method.equals("start")) {
				
				brushTool.beginPath();
				brushTool.lineTo(x, y);
			}
			else if(method.equals("drag")){
				brushTool.lineTo(x, y);
				brushTool.stroke();
			}
			else {
				brushTool.lineTo(x, y);
				brushTool.stroke();
				brushTool.closePath();
			}
		}
		else if(othermode.equals("line")) {
			if(method.equals("start")) {
				straightline = new StraightLine();
				straightline.setgraphics(brushTool);
				straightline.setcolor(Color.web(othercolor));
				straightline.setstart(x, y);
			}
			else if(method.equals("end")) {
				straightline.setend(x, y);
				straightline.draw();
			}
		}
		else if(othermode.equals("rectangle")) {
			if(method.equals("start")) {
				newrectangle = new Rectangleshape();
				newrectangle.setgraphics(brushTool);
				newrectangle.setcolor(Color.web(othercolor));
				newrectangle.setstart(x, y);
			}
			else if(method.equals("end")) {
				newrectangle.setend(x, y);
				newrectangle.setwidth();
				newrectangle.setheight();
				newrectangle.draw();
			}
			
		}
		else if(othermode.equals("circle")) {
			if(method.equals("start")) {
				newcircle = new Circleshape();
	            newcircle.setgraphics(brushTool);
	            newcircle.setcolor(Color.web(othercolor));
	            newcircle.setcenter(x, y);
			}
			else if(method.equals("end")) {
				newcircle.setend(x, y);
	            newcircle.setRadius();     
	            newcircle.draw();
			}
		}
		else if(othermode.equals("triangle")) {
			if(method.equals("start")) {
				newtriangle = new Triangleshape();
	            newtriangle.setgraphics(brushTool);
	            newtriangle.setcolor(Color.web(othercolor));
	            newtriangle.setstart(x, y);
			}
			else if(method.equals("end")) {
				newtriangle.setend(x, y);
	            newtriangle.setthird();     
	            newtriangle.draw();
			}
		}
		else if(othermode.equals("text")) {
			if(!method.equals("end")) {
				brushTool.setFill(Color.web(othercolor));
				brushTool.fillText(method, x, y);
			}
		}
	}
	
	
	@FXML
	public void mouse_draw(MouseEvent event) throws RemoteException {
		brushTool=canvas.getGraphicsContext2D();
		brushTool.setStroke(colorpicker.getValue());
		if(mode.equals("free")) {
			brushTool.beginPath();
			brushTool.lineTo(event.getX(), event.getY());
			
			
			this.whiteboard.broadcastobjects(this.whiteboardid, this.id, mode, "start", event.getX(), event.getY(), colorpicker.getValue().toString());
		}
		else if (mode.equals("line")){
			brushTool.setStroke(colorpicker.getValue());
			straightline = new StraightLine();
			straightline.setgraphics(brushTool);
			straightline.setcolor(colorpicker.getValue());
			straightline.setstart(event.getX(), event.getY());
			this.whiteboard.broadcastobjects(this.whiteboardid, this.id, mode, "start", event.getX(), event.getY(), colorpicker.getValue().toString());
		}
		else if(mode.equals("rectangle")) {
			brushTool.setStroke(colorpicker.getValue());
            brushTool.setFill(colorpicker.getValue());
            newrectangle = new Rectangleshape();
            newrectangle.setgraphics(brushTool);
            newrectangle.setcolor(colorpicker.getValue());
            newrectangle.setstart(event.getX(), event.getY());
            this.whiteboard.broadcastobjects(this.whiteboardid, this.id, mode, "start", event.getX(), event.getY(), colorpicker.getValue().toString());
		}
		else if(mode.equals("circle")) {
            brushTool.setStroke(colorpicker.getValue());
            brushTool.setFill(colorpicker.getValue());
            newcircle = new Circleshape();
            newcircle.setgraphics(brushTool);
            newcircle.setcolor(colorpicker.getValue());
            newcircle.setcenter(event.getX(), event.getY());
            this.whiteboard.broadcastobjects(this.whiteboardid, this.id, mode, "start", event.getX(), event.getY(), colorpicker.getValue().toString());
        }
		else if(mode.equals("triangle")) {
            brushTool.setStroke(colorpicker.getValue());
            brushTool.setFill(colorpicker.getValue());
            newtriangle = new Triangleshape();
            newtriangle.setgraphics(brushTool);
            newtriangle.setcolor(colorpicker.getValue());
            newtriangle.setstart(event.getX(), event.getY());
            this.whiteboard.broadcastobjects(this.whiteboardid, this.id, mode, "start", event.getX(), event.getY(), colorpicker.getValue().toString());
		}
		else if(mode.equals("text")) {
			brushTool.setStroke(colorpicker.getValue());
			brushTool.setFill(colorpicker.getValue());
			brushTool.fillText(canvastext, event.getX(), event.getY());
			this.whiteboard.broadcastobjects(this.whiteboardid, this.id, mode, canvastext, event.getX(), event.getY(), colorpicker.getValue().toString());
		}
		
	}
	
	@FXML
	public void mouse_drag(MouseEvent event) throws RemoteException {
		if(mode == "free") {
			brushTool.lineTo(event.getX(), event.getY());
			brushTool.stroke();
			//freeLine.addPoint(event.getX(), event.getY());
			this.whiteboard.broadcastobjects(this.whiteboardid, this.id, "free", "drag", event.getX(), event.getY(), colorpicker.getValue().toString());
		}
		
	}
	
	@FXML
	public void mouse_released(MouseEvent event) throws RemoteException {
		if(mode.equals("free")) {
			brushTool.lineTo(event.getX(), event.getY());
			brushTool.stroke();
			brushTool.closePath();
			//freeLine.setend(event.getX(), event.getY());
		}
		else if (mode.equals("line")) {
			straightline.setend(event.getX(), event.getY());
			straightline.draw();
		}
		else if(mode.equals("rectangle")) {
			newrectangle.setend(event.getX(), event.getY());
			newrectangle.setwidth();
			newrectangle.setheight();
            newrectangle.draw();
		}
		else if(mode.equals("circle")) {           
            newcircle.setend(event.getX(), event.getY());
            newcircle.setRadius();     
            newcircle.draw();
        }
		else if(mode.equals("triangle")) {           
            newtriangle.setend(event.getX(), event.getY());
            newtriangle.setthird();     
            newtriangle.draw();
        }
		this.whiteboard.broadcastobjects(this.whiteboardid, this.id, mode, "end", event.getX(), event.getY(), colorpicker.getValue().toString());
	}
	
	@FXML
	public void toolselected(ActionEvent e) {
		toolSelected = true;
	}
	
	@FXML
	public void line(ActionEvent e) {
		mode = "line";
	}
	
	@FXML
	public void free(ActionEvent e) {
		mode = "free";
	}
	
	@FXML
	public void circle(ActionEvent e) {
		mode = "circle";
	}
	
	@FXML
	public void rectangle(ActionEvent e) {
		mode = "rectangle";
	}
	
	@FXML
	public void triangle(ActionEvent e) {
		mode = "triangle";
	}
	
	@FXML
	public void canvasexit(ActionEvent e) {
		System.exit(0);
	}
	
	public void exitinguser() {
		System.exit(0);
	}
	
	public void gettextfromcanvascontroller(String data) {
		canvastext=data;
	}
	 @FXML
	 public void writetextfunction(ActionEvent event) {
		mode="text";
		Parent canvastext;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Text_input.fxml"));
			canvastext = (Parent)loader.load();
			canvastextcontroller controller = loader.getController();
			controller.setparentcontroller(this);
			Scene canvastextScene = new Scene(canvastext);
			//Stage canvastextStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Stage canvastextStage = new Stage();
			//canvastextStage.hide();
			canvastextStage.setScene(canvastextScene);
			canvastextStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	 }
	 @FXML
	 void messagebuttonaction(ActionEvent event) {
		 String message = this.chattextfield.getText();
		 this.chattextfield.clear();
		 try {
			 
			 this.chattextarea.appendText("\n"+Integer.toString(this.id)+":"+this.usernameString+": "+message);
			this.whiteboard.broadcastmessages(this.whiteboardid, this.id, message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	   @FXML
	    void saveaction(ActionEvent event) {
		   	if(userstatus_label.getText().equals("Manager")) {
			   	FileChooser savefile = new FileChooser();
		        savefile.setTitle("Save File");
		        File file = savefile.showSaveDialog(WhiteboardClient.mainstage);
		
		        if (file != null) {
		            try {
		                WritableImage writableImage = new WritableImage(921, 638);
		                canvas.snapshot(null, writableImage);
		                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
		                ImageIO.write(renderedImage, "png", file);
		            } catch (IOException e) {
		            	Dialog<String> dialog = new Dialog<String>();
				      	dialog.setTitle("Error");
				      	ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
				      	dialog.setContentText("IOException occurred");
				      	dialog.getDialogPane().getButtonTypes().add(type);
				      	dialog.showAndWait();
		            }
		        }
		   	}
		   	else {
		   		Dialog<String> dialog = new Dialog<String>();
		      	dialog.setTitle("Error");
		      	ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		      	dialog.setContentText("Unauthorized action, You are not a manager");
		      	dialog.getDialogPane().getButtonTypes().add(type);
		      	dialog.showAndWait();
			}
	   }
	   
	   @FXML
	    void newcanvas(ActionEvent event) {
		   if(userstatus_label.getText().equals("Manager")) {
			   brushTool.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			   try {
				this.whiteboard.updatecanvas(whiteboardid, id);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   else {
	        	Dialog<String> dialog = new Dialog<String>();
		      	dialog.setTitle("Error");
		      	ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		      	dialog.setContentText("Unauthorized action, You are not a manager");
		      	dialog.getDialogPane().getButtonTypes().add(type);
		      	dialog.showAndWait();
	        }
		   
	    }
	   
	   void updatecanvas() {
		   brushTool.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	   }
	   
	   @FXML
	    void openimage(ActionEvent event) {
			if(userstatus_label.getText().equals("Manager")) {
				FileChooser openFile = new FileChooser();
		        openFile.setTitle("Open File");
		        File file = openFile.showOpenDialog(WhiteboardClient.mainstage);

		        if (file != null) {
		            try {
		                InputStream io = new FileInputStream(file);
		                Image img = new Image(io);
		                canvas.getGraphicsContext2D().drawImage(img, 0, 0);
		            } catch (IOException ex) {
		            	Dialog<String> dialog = new Dialog<String>();
				      	dialog.setTitle("Error");
				      	ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
				      	dialog.setContentText("ICException has occurred");
				      	dialog.getDialogPane().getButtonTypes().add(type);
				      	dialog.showAndWait();
		            }
		        }

			}
			else {
	        	Dialog<String> dialog = new Dialog<String>();
		      	dialog.setTitle("Error");
		      	ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		      	dialog.setContentText("Unauthorized action, You are not a manager");
		      	dialog.getDialogPane().getButtonTypes().add(type);
		      	dialog.showAndWait();
	        }
	   }
	   
}
	   
