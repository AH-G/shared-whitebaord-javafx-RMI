package shapes;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Circleshape {
	
	private GraphicsContext canvasContext;
	private Color colorPickeruniversal;
	

    private double centerX, centerY, endX, endY;

    private Circle circle = new Circle();

    public Circleshape(){}

    public void setgraphics(GraphicsContext canvasContext) {
		this.canvasContext = canvasContext;
	}

    public void setcolor(Color colorPicker) {
		colorPickeruniversal = colorPicker;
	}
    public void setcenter(double centerX, double centerY){
        this.centerX = centerX;
        this.centerY = centerY;
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
    }

    public void setend(double endX, double endY){
        this.endX = endX;
        this.endY = endY;
    }
    public void setRadius(){
        circle.setRadius((Math.abs(endX - centerX) + Math.abs(endY - centerY)) / 2);
    }
    public void setRadius(double radius){
        circle.setRadius(radius);
    }
   

    public double getRadius(){
        return circle.getRadius();
    }

    public double getCenterX(){
        return circle.getCenterX();
    }

    public double getCenterY(){
        return circle.getCenterY();
    }

    public Color getColor(){
        return colorPickeruniversal;
    }


    public void draw(){
        canvasContext.setStroke(colorPickeruniversal);
        canvasContext.setFill(colorPickeruniversal);
        canvasContext.fillOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
        canvasContext.strokeOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
    }
}
