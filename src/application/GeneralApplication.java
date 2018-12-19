package application;
import ui.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
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
	private ApplicationFactory appFactory;
	
	/**
	 * Set up stage for the GUI
	 * @param primaryStage - the primary stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception{
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource(guiPath));
			Parent root = loader.load();
			Controller controller = loader.getController();
			controller.setStage(primaryStage);
			controller.setAppFactory(appFactory);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			Screen primaryScreen = Screen.getPrimary();
			Rectangle2D primaryScreenBounds = primaryScreen.getVisualBounds();
			double stageX = primaryScreenBounds.getMinX() + (primaryScreenBounds.getWidth() - primaryStage.getWidth()) / 2;
			double stageY = primaryScreenBounds.getMinY() + (primaryScreenBounds.getHeight() - primaryStage.getHeight()) / 2;
			primaryStage.setX(stageX);
			primaryStage.setY(stageY);
			primaryStage.show();
	} 
	
	public void setGUIPath(String guiPath) {
		this.guiPath = guiPath;
	}
	
	/**
	 * For Unit Testing purposes
	 */
	public String getGUIPath(){
		return guiPath;
	}
	
	public void setAppFactory(ApplicationFactory factory) {
		this.appFactory = factory;
	}

}
