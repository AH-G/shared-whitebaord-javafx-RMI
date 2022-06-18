package server;

import java.util.Vector;


import client.whiteboardclientinterface;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
public class Whiteboardserver extends UnicastRemoteObject implements whiteboardserverinterface {
	
	Hashtable<Integer, String> usernames = new Hashtable<Integer, String>();
	Hashtable<Integer, whiteboardclientinterface> userobjects = new Hashtable<Integer, whiteboardclientinterface>();
	ArrayList<Integer> idlist = new ArrayList<Integer>();
	Hashtable<Integer, whiteboardproperties> whiteboardid = new Hashtable<Integer, whiteboardproperties>();	
	ArrayList<Integer> whiteboardList = new ArrayList<Integer>();
	
	
	public Whiteboardserver() throws RemoteException{
		super();
	}
	public int registeruser(String name, whiteboardclientinterface client) throws RemoteException {
		// TODO Auto-generated method stub
		
		int id;
		int max = -1;
		if (!idlist.isEmpty()) {
			
			max=Collections.max(idlist);
			id = max+1;
			idlist.add(id);
			usernames.put(id, name);
			userobjects.put(id, client);
			//usercontroller.put(id,controller);
		}
		else {
			id=0;
			idlist.add(id);
			usernames.put(id, name);
			userobjects.put(id, client);
		//	usercontroller.put(id, controller);
		}
		
		
		
		return id;
	}

	public void newmember() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public Vector getmemebers() throws RemoteException {
		// TODO Auto-generated method stub
		
		return null;
	}

	public boolean removeuser() throws RemoteException {
		// TODO Auto-generated method stub
		
		return false;
	}

	public boolean sendobjects() throws RemoteException {
		// TODO Auto-generated method stub
		
		return false;
	}

	public boolean broadcastobjects(int whiteboardidint, int id, String mode, String method, double x, double y, String color) throws RemoteException {
		// TODO Auto-generated method stub
		whiteboardproperties whiteboardobject = whiteboardid.get(whiteboardidint);
		whiteboardobject.writewhiteboardhistory(method, mode, x, y, color);
		whiteboardclientinterface temp;
		ArrayList<Integer> tempusersArrayList= whiteboardobject.getcollaborators();
		for(int i=0;i<tempusersArrayList.size();i++) {
			if (tempusersArrayList.get(i)!=id) {
				userobjects.get(tempusersArrayList.get(i)).receivedraw(mode, method, x, y, color);
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		String hostname = "localhost";
		String servicename = "Whiteboardservice";
		try{
			whiteboardserverinterface whiteboard = new Whiteboardserver();
			Naming.rebind("rmi://"+hostname+"/"+servicename, whiteboard);
			System.out.println("Whiteboard RMI Server is running...");
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> getwhiteboard() throws RemoteException {
		// TODO Auto-generated method stub
		return this.whiteboardList;
	}
	
	public int createwhiteboard(int clientid) throws RemoteException {
		// TODO Auto-generated method stub
		int id;
		int max = -1;
		if (!whiteboardList.isEmpty()) {
			max=Collections.max(whiteboardList);
			id = max+1;
			whiteboardList.add(id);
			whiteboardproperties newhiteboard = new whiteboardproperties(id);
			newhiteboard.setmanager(clientid);
			whiteboardid.put(id, newhiteboard);
		}
		else {
			id=0;
			whiteboardList.add(id);
			whiteboardproperties newhiteboard = new whiteboardproperties(id);
			newhiteboard.setmanager(clientid);
			whiteboardid.put(id, newhiteboard);
		}
		
		return id;
	}
	
	public void gethistory(int whiteboardclientid, int clientid) throws RemoteException{
		whiteboardproperties tempWhiteboard = whiteboardid.get(whiteboardclientid);
		int gethistorysize = tempWhiteboard.newwhiteboardhistory.method.size();
		if(gethistorysize!=0) {
			System.out.println("checking the connectwhiteboard function "+ tempWhiteboard.newwhiteboardhistory.modeArrayList.get(0)); 
			for(int i=0; i<gethistorysize;i++) {
					userobjects.get(clientid).receivedraw(tempWhiteboard.newwhiteboardhistory.modeArrayList.get(i), tempWhiteboard.newwhiteboardhistory.method.get(i), tempWhiteboard.newwhiteboardhistory.pointX.get(i), tempWhiteboard.newwhiteboardhistory.pointY.get(i), tempWhiteboard.newwhiteboardhistory.colorArrayList.get(i) );
			}
		}
	}
	
	public void connectwhiteboard(int whiteboardclientid, int clientid) throws RemoteException {
		// TODO Auto-generated method stub
		whiteboardproperties tempWhiteboard = whiteboardid.get(whiteboardclientid);
		tempWhiteboard.addcollaboarators(clientid);
		ArrayList<Integer> tempArrayList = tempWhiteboard.getcollaborators();
		for(int i=0;i<tempArrayList.size();i++) {
			if(tempArrayList.get(i)!=clientid) {
				userobjects.get(tempArrayList.get(i)).updateusersonline();
			}
		}
	}
	public ArrayList<String> getcollaboratoronline(int whiteboardclientid) throws RemoteException {
		whiteboardproperties tempWhiteboard = whiteboardid.get(whiteboardclientid);
		ArrayList<Integer> tempArrayList = tempWhiteboard.getcollaborators();
		ArrayList<String> tempStrings = new ArrayList<String>();
		for(int i=0;i<tempArrayList.size();i++) {
				tempStrings.add(Integer.toString(tempArrayList.get(i))+":"+usernames.get(tempArrayList.get(i)));
		}
		return tempStrings;
	}
	public int getmanager(int whiteboardclientid) throws RemoteException {
		whiteboardproperties tempWhiteboard = whiteboardid.get(whiteboardclientid);
		return tempWhiteboard.getmanager();
		
	}
	public void kickuser(int whiteboardclientid, int userid) throws RemoteException{
		whiteboardproperties tempWhiteboard = whiteboardid.get(whiteboardclientid);
		tempWhiteboard.collaboratorid.remove(userid);
		whiteboardclientinterface tempuserobject=userobjects.get(userid);
		idlist.remove(userid);
		usernames.remove(userid);
		userobjects.remove(userid);
		ArrayList<Integer> tempArrayList = tempWhiteboard.getcollaborators();
		
		for(int i=0;i<tempArrayList.size();i++) {
			
			userobjects.get(tempArrayList.get(i)).updateusersonline();
		}
		tempuserobject.exitingkickuser();
		
	}
	public void updatecanvas(int whiteboardidint, int userid) throws RemoteException{
		whiteboardproperties whiteboardobject = whiteboardid.get(whiteboardidint);
		ArrayList<Integer> tempusersArrayList= whiteboardobject.getcollaborators();
		for(int i=0;i<tempusersArrayList.size();i++) {
			if (tempusersArrayList.get(i)!=userid) {
				userobjects.get(tempusersArrayList.get(i)).updatecanvas();
			}
		}
	}
	
	public boolean broadcastmessages(int whiteboardidint, int id, String message) throws RemoteException {
		// TODO Auto-generated method stub
		whiteboardproperties whiteboardobject = whiteboardid.get(whiteboardidint);
		whiteboardclientinterface temp;
		ArrayList<Integer> tempusersArrayList= whiteboardobject.getcollaborators();
		String newmessage = Integer.toString(id)+":"+usernames.get(id)+": "+message;
		
		for(int i=0;i<tempusersArrayList.size();i++) {
			if (tempusersArrayList.get(i)!=id) {
				userobjects.get(tempusersArrayList.get(i)).receivedmessage(newmessage);
			}
		}
		return false;
	}
}
