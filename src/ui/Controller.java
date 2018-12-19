package ui;
import javafx.stage.Stage;
import application.ApplicationFactory;

/**
 * An interface for GUI controllers in this program. 
 * Created to be able to create a generalized Application class.
 * @author elysamuel16
 *
 */
public interface Controller {

	/**
	 * Sets the primary stage the this controller will use to switch to other windows 
	 * @param stage
	 */
	public void setStage(Stage stage);
	
	/**
	 * Sets the factory this controller will use to generate other applications
	 * @param factory
	 */
	public void setAppFactory(ApplicationFactory factory);
}
