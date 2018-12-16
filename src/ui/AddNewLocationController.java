package ui;
import java.net.URL;

import javafx.stage.Stage;

import java.util.ResourceBundle;

import application.AddNewLocationApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Connects the AddRidePost FXML GUI file with the backend operations
 * @author elysamuel16
 */
public class AddNewLocationController{

	private AddNewLocationApplication application;
	private Stage stage;
	
	@FXML
	private URL location;
	@FXML
	private ResourceBundle resources;
	@FXML 
	private TextField locationTextField;
	
	public AddNewLocationController() {
		locationTextField = new TextField();
	}
	
	/**
	 * Method to be called automatically when this controller is attached to the FXML file
	 */
	@FXML
	private void initialize() {
		
	}
	
	public void setApplication(AddNewLocationApplication application) {
		this.application = application;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * Method to assign the value of the textLocation field to the newLocation variable 
	 * in the corresponding application class, then close the stage this controller is controlling.
	 * The GUI calls this method when the appropriate button is pressed.
	 */
	public void updateNewLocation() {
		String newLocation = locationTextField.getText();
		application.setNewLocation(newLocation);
		stage.close();
	}
	
	/**
	 * Method to close the stage that this controller is controlling.
	 * The GUI calls this method when the appropriate button is pressed. 
	 */
	public void closeStage() {
		stage.close();
	}
	
}
