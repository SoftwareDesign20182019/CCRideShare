package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import ui.RideListController;


/**
 * Class responsible for holding the main GUI
 * @author kbhat
 *
 */
public class AddRideRequestApplication extends Application {
	
	/**
	 * Set up stage for the GUI
	 * @param primaryStage - the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/AddRideRequestGUI.fxml"));
			Parent root = loader.load();
			ui.AddRideRequestController controller = loader.getController();
			controller.setPrimaryStage(primaryStage);
			//Parent root = FXMLLoader.load(getClass().getResource("/ui/RideListGUI.fxml"));
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
