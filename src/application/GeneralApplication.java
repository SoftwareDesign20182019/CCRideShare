package application;
import ui.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import ui.RideListController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GeneralApplication extends Application{
	
	private String guiPath;
	private Controller controller;
	
	/**
	 * Set up stage for the GUI
	 * @param primaryStage - the primary stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception{
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource(guiPath));
			Parent root = loader.load();
			controller = loader.getController();
			controller.setStage(primaryStage);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
	} 
	
	public void setGUIPath(String guiPath) {
		this.guiPath = guiPath;
	}
	
	/**
	 * For Unit Testing purposes
	 */
	public Controller getController(){
		return controller;
	}

}