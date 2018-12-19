package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
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
	
	private RideListController controller;
	private ListTab tab;
	private ApplicationFactory appFactory;
	
	//stage positioning constants
	private static final Screen PRIMARY_SCREEN = Screen.getPrimary();
	private static final Rectangle2D PRIMARY_SCREEN_BOUNDS = PRIMARY_SCREEN.getVisualBounds();
	
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
			controller.setAppFactory(appFactory);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			double stageX = PRIMARY_SCREEN_BOUNDS.getMinX() + (PRIMARY_SCREEN_BOUNDS.getWidth() - primaryStage.getWidth()) / 2;
			double stageY = PRIMARY_SCREEN_BOUNDS.getMinY() + (PRIMARY_SCREEN_BOUNDS.getHeight() - primaryStage.getHeight()) / 2;
			primaryStage.setX(stageX);
			primaryStage.setY(stageY);
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
	
	public void setAppFactory(ApplicationFactory factory) {
		this.appFactory = factory;
	}
}
