package application;
import ui.CreateAccountGUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import ui.RideShareGUIController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.RideShareGUIController;

public class CreateAccountApplication extends Application{
	/**
	 * Set up stage for the GUI
	 * @param primaryStage - the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/CreateAccountGUI.fxml"));
			Parent root = loader.load();
			CreateAccountGUIController controller = loader.getController();
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
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseHandler.initialize();
		launch(args);
	}
}
