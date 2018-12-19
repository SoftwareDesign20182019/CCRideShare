package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class RideDetailsApplication extends Application {
	
	private RidePost ridePost;
	
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
