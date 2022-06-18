package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class StraightLine {
	private GraphicsContext canvasContext;
	private Color colorPickeruniversal;
	private double startX, startY, endX, endY;
	public void StraightLine() {
		
	}
	public void setgraphics(GraphicsContext canvasContext) {
		this.canvasContext = canvasContext;
	}
	public void setcolor(Color colorPicker) {
		colorPickeruniversal = colorPicker;
	}
	public void setstart(double x, double y) {
		this.startX=x;
		this.startY=y;
	}
	public void setend(double x, double y) {
		this.endX=x;
		this.endY=y;
	}
	public void draw() {
		canvasContext.setStroke(colorPickeruniversal);
		canvasContext.strokeLine(this.startX, this.startY, this.endX, this.endY);
	}
}
