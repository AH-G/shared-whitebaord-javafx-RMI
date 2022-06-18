package client;

import java.rmi.RemoteException; 
import java.io.IOException; 
import java.rmi.Remote; 
import java.util.Vector;

public interface whiteboardclientinterface extends Remote {

	public void setgui(MainController client)throws RemoteException;
	public MainController getcontroller()throws RemoteException;
	public boolean receivedraw(String othermode, String method, double x, double y, String othercolor) throws RemoteException;
	public boolean receivedmessage(String message) throws RemoteException;
	public void updateusersonline() throws RemoteException;
	public void exitingkickuser() throws RemoteException;
	public void updatecanvas() throws RemoteException;
}

