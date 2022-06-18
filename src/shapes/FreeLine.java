package shapes;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public class FreeLine {
	private GraphicsContext canvasContext;
	private ColorPicker colorPickeruniversal;
	private double startX, startY, endX, endY;
	private ArrayList<Double> xValues = new ArrayList<>();
    private ArrayList<Double> yValues = new ArrayList<>();
	public FreeLine() {
	}
    
	public void setgraphics(GraphicsContext canvasContext) {
		this.canvasContext = canvasContext;
	}
	public void setcolor(ColorPicker colorPicker) {
		colorPickeruniversal = colorPicker;
	}
	public void setstart(double x, double y) {
		this.startX=x;
		this.startY=y;
	}
	public void addPoint(double x, double y){
        xValues.add(x);
        yValues.add(y);
	}	
	public void setend(double x, double y) {
		this.endX=x;
		this.endY=y;
	}
	public void draw() {
		canvasContext.setStroke(colorPickeruniversal.getValue());
		canvasContext.beginPath();
		canvasContext.lineTo(this.startX, this.startY);
		for(int i = 0; i < xValues.size(); i++){
            canvasContext.lineTo(xValues.get(i), yValues.get(i));
            canvasContext.stroke();
        }
		canvasContext.lineTo(this.endX, this.endY);
		canvasContext.stroke();
		canvasContext.closePath();
	}
}
