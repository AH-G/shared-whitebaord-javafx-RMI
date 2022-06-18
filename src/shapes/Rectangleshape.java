package shapes;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rectangleshape {
    
    private GraphicsContext canvasContext;
	private Color colorPickeruniversal;
	
    public double startX, startY, endX, endY, width, height;
    private Rectangle rectangle = new Rectangle();
    
    public Rectangleshape(){ }
    public void setgraphics(GraphicsContext canvasContext) {
		this.canvasContext = canvasContext;
	}
    public void setcolor(Color colorPicker) {
		colorPickeruniversal = colorPicker;
	}
    public void setstart(double x, double y){
        this.startX = x;
        this.startY = y;

        rectangle.setX(x);
        rectangle.setY(y);
    }
    public void setend(double x, double y){
        this.endX = x;
        this.endY = y;
    }
    public void setwidth(){
        this.width = Math.abs((endX - startX));
        rectangle.setWidth(Math.abs((endX - startX)));
    }
    public void setheight(){
        this.height = Math.abs((endY - startY));
        rectangle.setHeight(Math.abs((endY - startY)));
    }

    public boolean containsPoint(Point2D point){
        return rectangle.contains(point);
    }

    public double getX(){
        return rectangle.getX();
    }

    public double getY(){
        return rectangle.getY();
    }

    public double getWidth(){
        return rectangle.getWidth();
    }

    public double getHeight() {
        return rectangle.getHeight();
    }

    public Color getColor(){
        return colorPickeruniversal;
    }

    public Color getFill(){
        return colorPickeruniversal;
    }

    public void draw(){
        canvasContext.setStroke(colorPickeruniversal);
        canvasContext.setFill(colorPickeruniversal);
        canvasContext.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        canvasContext.strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }
}
