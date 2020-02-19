import javafx.geometry.Point2D;

public interface GardenComponent {
	public void move(double X, double Y);
	public boolean ContainsPoint(Point2D point);
}
