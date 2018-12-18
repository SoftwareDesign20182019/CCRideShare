package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.LogInController;

/**
 * The Application class where the overall javafx application begins. It is currently the login screen. 
 * @author elysamuel16
 *
 */
public class LogInApplication extends Application {
	/**
	 * Set up stage for the GUI
	 * @param primaryStage - the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/LogInGUI.fxml"));
			Parent root = loader.load();
			LogInController controller = loader.getController();
			controller.setStage(primaryStage);
			Scene scene = new Scene(root);
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
		launch(args);
	}
}
