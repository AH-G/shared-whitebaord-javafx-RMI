package server;

import java.util.ArrayList;

public class whiteboardproperties {
	public int whiteboardid;
	public int managerid;
	public ArrayList<Integer> collaboratorid;
	public whiteboardhistory newwhiteboardhistory;
	
	public whiteboardproperties(int whiteboardid) {
		this.whiteboardid = whiteboardid;
		newwhiteboardhistory = new whiteboardhistory();
	}
	
	public void writewhiteboardhistory(String method, String mode, double x, double y, String color) {
		this.newwhiteboardhistory.putintohistory(method, mode, x, y, color);
	}
	public void addcollaboarators(int id) {
		
		if (this.collaboratorid == null) {
			this.collaboratorid = new ArrayList<Integer>();
			
			this.collaboratorid.add(id);
		}
		else if(!this.collaboratorid.contains(id)) {
			this.collaboratorid.add(id);
		}
	}
	public void setmanager(int id) {
		this.managerid=id;
	}
	public ArrayList<Integer> getcollaborators(){
		return this.collaboratorid;
	}
	public int getmanager() {
		return this.managerid;
	}
}
