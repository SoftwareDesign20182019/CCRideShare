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
	
	//stage positioning constants
	private static final Screen PRIMARY_SCREEN = Screen.getPrimary();
	private static final Rectangle2D PRIMARY_SCREEN_BOUNDS = PRIMARY_SCREEN.getVisualBounds();
	
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
			double stageX = PRIMARY_SCREEN_BOUNDS.getMinX() + (PRIMARY_SCREEN_BOUNDS.getWidth() - primaryStage.getWidth()) / 2;
			double stageY = PRIMARY_SCREEN_BOUNDS.getMinY() + (PRIMARY_SCREEN_BOUNDS.getHeight() - primaryStage.getHeight()) / 2;
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
