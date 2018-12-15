package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.AddRidePostController;

/**
 * Class responsible for holding the GUI for listing a new ride
 * @author elysamuel16
 *
 */
public class AddRidePostApplication extends Application {
	
	/**
	 * Set up stage for the GUI
	 * @param primaryStage - the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/AddRidePostGUI.fxml"));
			Parent root = loader.load();
			AddRidePostController controller = loader.getController();
			controller.setPrimaryStage(primaryStage);
			//Parent root = FXMLLoader.load(getClass().getResource("/ui/AddRidePostGUI.fxml"));
			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
}
