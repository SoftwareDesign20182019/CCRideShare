package application;
import ui.CreateAccountGUIController;
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

public class CreateAccountApplication extends Application{
	/**
	 * Set up stage for the GUI
	 * @param primaryStage - the primary stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception{
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/CreateAccountGUI.fxml"));
			Parent root = loader.load();
			CreateAccountGUIController controller = loader.getController();
			controller.setPrimaryStage(primaryStage);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
	} 
		
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseHandler.initialize();
		launch(args);
	}
}
