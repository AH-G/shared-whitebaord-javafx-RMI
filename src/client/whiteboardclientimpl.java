package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;




public class whiteboardclientimpl extends UnicastRemoteObject implements whiteboardclientinterface {
	
	protected whiteboardclientimpl(MainController client) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.setgui(client);
	}
	public MainController client;
	public static int id;
	public static String name;
	
	public void setgui(MainController client)throws RemoteException{
		this.client=client;
	}
	public MainController getcontroller() throws RemoteException{
		return this.client;
	}
	public void updateusersonline() throws RemoteException{
		this.client.updateusersonline();
	}
	public void exitingkickuser() throws RemoteException{
		this.client.exitinguser();
	}
	public void updatecanvas() throws RemoteException{
		this.client.updatecanvas();
	}
	public boolean receivedraw(String othermode, String method, double x, double y, String othercolor) throws RemoteException {
		
		this.client.other_draw(othermode, method, x, y, othercolor);
		return true;
	}

	public boolean receivedmessage(String message) throws RemoteException {
		
		this.client.putmessage(message);
		return false;
	}

}
