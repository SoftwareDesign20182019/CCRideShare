package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
/**
 * class responsible for launching ride details gui when a post is clicked on
 *
 */
public class RideDetailsApplication extends Application {
	
	private RidePost ridePost;
	
	/**
	 * Set up stage for the GUI
	 * @param primaryStage - the primary stage
	 */
	public void start(Stage primaryStage) {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/RideDetailsGUI.fxml"));
			Parent root = loader.load();
			ui.RideDetailsController controller = loader.getController();
			controller.setRidePost(ridePost);
			controller.assignGUIvalues();
			controller.setStage(primaryStage);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.showAndWait();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setRidePost(RidePost ridePost) {
		this.ridePost = ridePost;
	}
}
