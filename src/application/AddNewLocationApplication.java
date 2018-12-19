package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.AddNewLocationController;
import ui.AddRidePostController;

/**
 * Class responsible for holding the GUI for listing a new ride
 * @author elysamuel16
 *
 */
public class AddNewLocationApplication extends Application {
	
	private String newLocation;
	private ApplicationFactory appFactory;
	
	/**
	 * Set up stage for the GUI
	 * @param primaryStage - the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/AddNewLocationGUI.fxml"));
			Parent root = loader.load();
			ui.AddNewLocationController controller = loader.getController();
			controller.setApplication(this);
			controller.setStage(primaryStage);
			controller.setAppFactory(appFactory);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.showAndWait();
		} 
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the new location that the user has entered
	 * This is the only method that should be called by an AddRidePostController or an AddRideRequestController.
	 * @return the String the user enters as their new location
	 */
	public String getNewLocation() {
		return newLocation;
	}
	
	/**
	 * newLocation setter (to be called by this application's controller)
	 * @param newLocation the value the user entered as a new location
	 */
	public void setNewLocation(String newLocation) {
		this.newLocation = newLocation;
	}
	
	public void setAppFactory(ApplicationFactory factory) {
		this.appFactory = factory;
	}
}
