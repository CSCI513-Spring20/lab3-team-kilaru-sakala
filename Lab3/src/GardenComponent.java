import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public interface GardenComponent {
	public void move(double X, double Y);
	public boolean ContainsPoint(Point2D point);
	public void setPosition(Point2D point);
	public void setLineColor(Color color);
}
