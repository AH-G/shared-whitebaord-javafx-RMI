package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Triangleshape {
    private GraphicsContext canvasContext;
	private Color colorPickeruniversal;
	
	public double startX, startY, endX, endY, thirdX, thirdY;
    private Polygon triangle = new Polygon();
	public Triangleshape() {}
	public void setgraphics(GraphicsContext canvasContext) {
		this.canvasContext = canvasContext;
	}
    public void setcolor(Color colorPicker) {
		colorPickeruniversal = colorPicker;
	}
    public void setstart(double x, double y){
        this.startX = x;
        this.startY = y;
    }
    public void setend(double x, double y){
        this.endX = x;
        this.endY = y;
    }
    public void setthird() {
    	if(this.startX < this.endX) {
    		this.thirdX=Math.abs(this.startX-Math.abs((this.endX-this.startX)));
    		this.thirdY=this.endY;
    	}
    	else {
    		this.thirdX=Math.abs(this.startX+Math.abs((this.endX-this.startX)));
    		this.thirdY=this.endY;
    	}
    }
    public void draw(){
        canvasContext.setStroke(colorPickeruniversal);
        canvasContext.setFill(colorPickeruniversal);
        setthird();
        canvasContext.fillPolygon(new double[]{this.startX, this.endX,this.thirdX}, new double[]{this.startY, this.endY, this.thirdY}, 3);
        canvasContext.strokePolygon(new double[]{this.startX, this.endX,this.thirdX}, new double[]{this.startY, this.endY, this.thirdY}, 3);
    }
    
    
}
