import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FlowerBed implements GardenComponent{
	Point2D topLeft;
	int height;
	int width;
	Color rectangleColor;
	Rectangle rectangle = new Rectangle();
	List<GardenComponent> innerComponents = new ArrayList<GardenComponent>();
	
	public FlowerBed(Point2D point, int height, int width) {
		this.topLeft = point;
		this.height = height;
		this.width = width;
		rectangle.setHeight(height);
		rectangle.setWidth(width);
		rectangle.setX(topLeft.getX());
		rectangle.setY(topLeft.getY());
		rectangle.setFill(null);
		setLineColor(Color.DARKGOLDENROD);
		
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}

	@Override
	public void move(double dx, double dy) {
		rectangle.setX(rectangle.getX()+dx);
		rectangle.setY(rectangle.getY()+dy);	
		for(GardenComponent child: innerComponents){
			child.move(dx,dy);
		}
	}
	
	@Override
	public boolean ContainsPoint(Point2D point) {
		boolean inRectangle = (point.getX() >= rectangle.getX() && point.getX() <= rectangle.getX()+rectangle.getWidth()
				&& point.getY()>= rectangle.getY() && point.getY() <= rectangle.getY()+rectangle.getHeight());
		return inRectangle;
		
	}
	
	public void addChild(GardenComponent shape){
		innerComponents.add(shape);
		shape.setLineColor(rectangleColor);
	}
	
	public void removeChild(GardenComponent shape){
		if(innerComponents.contains(shape)){
			innerComponents.remove(shape);
			shape.setLineColor(rectangleColor);
		}
	}

	@Override
	public void setPosition(Point2D point) {
		topLeft = point;
		
	}

	@Override
	public void setLineColor(Color color) {
		rectangleColor = color;	
		rectangle.setStroke(rectangleColor);
		
	}
	
}
