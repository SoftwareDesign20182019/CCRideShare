
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



/**
 *
 * @author elysamuel16
 */
public class RideListGUI extends Application {
	private int STAGE_WIDTH = 1000;
	private int STAGE_HEIGHT = 1000;
    
    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setWidth(STAGE_WIDTH);
    	primaryStage.setHeight(STAGE_HEIGHT);
    	
    	
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setTextFill(Color.RED);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
            	btn.setTextFill(Color.BLUE);
                System.out.println("Hello World!");
            }
        });
        
        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: transparent;");  
        layout.getChildren().add(btn);
        
        Scene scene = new Scene(layout, 600, 250);
        scene.setFill(Color.RED);
        
//        Rectangle r = new Rectangle(25,25,250,250);
//        r.setFill(Color.BLUE);
//        layout.getChildren().add(r);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
