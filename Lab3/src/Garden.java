import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Garden extends Application{

	Pane root;
	Scene scene;
	Flower flower;
	FlowerBed flowerBed;
	Point2D lastPosition = null;
	GardenComponent currentComponent;
	Point2D clickPoint;
	boolean inDragMode = false;
	List<GardenComponent> myComponents = new ArrayList<GardenComponent>();
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new AnchorPane();
		scene = new Scene(root, 500, 500);
		scene.setFill(Color.YELLOWGREEN);
		
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMousePressed(mouseHandler);
	    scene.setOnMouseReleased(mouseHandler);
		
		flower =  new Flower(new Point2D(20,20),10);
		myComponents.add(flower);
		root.getChildren().add(flower.getCircle());
		
		flowerBed = new FlowerBed(new Point2D(50,25),200,100);
		myComponents.add(flowerBed);
		root.getChildren().add(flowerBed.getRectangle());
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {	 
        @Override
        public void handle(MouseEvent event) {
        	
        	clickPoint = new Point2D(event.getX(),event.getY());  
        	
        	String eventName = event.getEventType().getName(); 
        	
        	// Return shape if target of mouse click  (otherwise return null)
        	if(!inDragMode){
        		currentComponent = getCurrentShape();
        	}
        	
        	switch(eventName){
        	case "MOUSE_DRAGGED":
        		
        		inDragMode = true;
        		if(currentComponent!=null && lastPosition != null){
        			double distanceX = clickPoint.getX()-lastPosition.getX();
        			double distanceY = clickPoint.getY()-lastPosition.getY();
        			currentComponent.move(distanceX, distanceY);
    			}
        		
        	break;
        	case "MOUSE_RELEASED":
        		
        		// If current shape is a circle and mouse released inside rectangle
        		if(currentComponent!=null && currentComponent instanceof Flower){
        			for(GardenComponent container: myComponents){
            			if (container instanceof FlowerBed && container.ContainsPoint(clickPoint)){
            				((FlowerBed)container).addChild(currentComponent);
            				break;
            			}
            			else if(container instanceof FlowerBed && !container.ContainsPoint(clickPoint)) {
            				((FlowerBed)container).removeChild(currentComponent);
            				break;
            			}
            			
            		} 
    			}
        		inDragMode = false;
        		break;
        	}
        	lastPosition = clickPoint;
        }               
     };
     
     private GardenComponent getCurrentShape(){
    	GardenComponent currentShape = null;
 		for(GardenComponent shape: myComponents){
 			if (shape.ContainsPoint(clickPoint)){
 				currentShape = shape;
 				break;
 			}
 		} 
 		return currentShape;
     }
     
     public static void main(String[] args) {
    	 launch(args);

	}

}