package client;

import java.rmi.Naming;


import javafx.application.Platform;
import javafx.stage.Stage;
import server.whiteboardserverinterface;


public class whiteboardclientmain extends Thread {
	public int checkid;

	public void setid(int id) {
		this.checkid=id;
	}
	public int getid() {
		return this.checkid;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		
		String hostName = "localhost";
		String serviceName = "Whiteboardservice";
		String username = "dummy";
		try{
			whiteboardserverinterface server = (whiteboardserverinterface)Naming.lookup("rmi://"+hostName+"/"+serviceName);
			MainController controller= new MainController();
			logincontroller logincontrollerinstance = new logincontroller();
			//String controllerid = controller.toString().substring(controller.toString().lastIndexOf("@") + 1);
			controller.setwhiteboardclass(server);
			logincontrollerinstance.setwhiteboardclass(server);
			logincontrollerinstance.setmaincontroller(controller);
			loginclient loginclass = new loginclient(logincontrollerinstance);
			
			loginclass.init();
	        Platform.startup(() -> {
	            // create primary stage
	            Stage stage = new Stage();
	            loginclass.start(stage);
	        });
			//guiclass.buildUI(args, server);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
