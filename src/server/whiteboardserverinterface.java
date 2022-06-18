package server;

import java.rmi.RemoteException; 
import java.io.IOException; 
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Vector;

import client.whiteboardclientinterface;

public interface whiteboardserverinterface extends Remote {
	public int registeruser(String name, whiteboardclientinterface client) throws RemoteException;
	public ArrayList<Integer> getwhiteboard() throws RemoteException;
	public int createwhiteboard(int clientid) throws RemoteException;
	public void connectwhiteboard(int whiteboardclientid, int clientid) throws RemoteException;
	public boolean removeuser() throws RemoteException;
	public boolean sendobjects() throws RemoteException;
	public boolean broadcastobjects(int whiteboardidint, int id, String mode, String method, double x, double y, String color ) throws RemoteException;
	public boolean broadcastmessages(int whiteboardidint, int id, String message) throws RemoteException;
	public ArrayList<String> getcollaboratoronline(int whiteboardclientid) throws RemoteException;
	public int getmanager(int whiteboardclientid) throws RemoteException;
	public void kickuser(int whiteboardid, int userid) throws RemoteException;
	public void updatecanvas(int whiteboardidint, int userid) throws RemoteException;
	public void gethistory(int whiteboardclientid, int clientid) throws RemoteException;
}

