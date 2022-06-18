package server;

import java.util.ArrayList;

public class whiteboardhistory {
	public ArrayList<String> method;
	public ArrayList<String> modeArrayList;
	public ArrayList<Double> pointX;
	public ArrayList<Double> pointY;
	public ArrayList<String> colorArrayList;
	
	public void putintohistory(String methodmethod, String methodmode, double methodpointX, double methodpointY, String methodcolor) {
		method.add(methodmethod);
		modeArrayList.add(methodmode);
		pointX.add(methodpointX);
		pointY.add(methodpointY);
		colorArrayList.add(methodcolor);
	}
	
	public whiteboardhistory() {
		this.method = new ArrayList<String>();
		this.modeArrayList = new ArrayList<String>();
		this.pointX = new ArrayList<Double>();
		this.pointY = new ArrayList<Double>();
		this.colorArrayList = new ArrayList<String>();
	}
	public ArrayList<String> getmethod(){
		return this.method;
	}
	public ArrayList<String> getmode(){
		return this.modeArrayList;
	}
	public ArrayList<Double> getpointX(){
		return this.pointX;
	}
	public ArrayList<Double> getpointY(){
		return this.pointY;
	}
	public ArrayList<String> getcolor(){
		return this.colorArrayList;
	}
}
