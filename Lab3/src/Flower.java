import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Flower implements GardenComponent{
	
	Point2D center;
	int radius;
	Circle circle;
	
	Point2D currentLocation;
	
	public Flower(Point2D point, int radius){
		circle = new Circle();
		this.center = point;
		this.radius = radius;
		circle.setCenterX(point.getX());
		circle.setCenterY(point.getY());
		circle.setRadius(radius);
		circle.setFill(Color.RED);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(1);
	}
	
	public Circle getCircle(){
		return circle;
	}
	
	
	@Override
	public void move(double dx, double dy) {
		circle.setCenterX(circle.getCenterX()+dx);
		circle.setCenterY(circle.getCenterY()+dy);
	}
	
	@Override
	public boolean ContainsPoint(Point2D point) {
		return (circle.contains(point));				
	}
	
	public Point2D getCurrentocation() {
		return currentLocation;
	}

}
