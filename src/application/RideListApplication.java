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
public class RideListApplication extends Application {
	
	public enum ListTab {
		RIDES, RIDE_REQUESTS
	}
	
	RideListController controller;
	ListTab tab;
	
	/**
	 * Set up stage for the GUI
	 * @param primaryStage - the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/RideListGUI.fxml"));
			Parent root = loader.load();
			controller = loader.getController();
			controller.setStage(primaryStage);
			//Parent root = FXMLLoader.load(getClass().getResource("/ui/RideListGUI.fxml"));
			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			if(tab != null) {
				controller.setTab(tab);
			}
		} 
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setTab(ListTab tab) {
		this.tab = tab;
		if(this.controller != null) {
			controller.setTab(tab);
		}
	}
}
