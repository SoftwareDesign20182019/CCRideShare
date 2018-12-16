package ui;
import java.net.URL;

import javafx.stage.Stage;

import java.util.ResourceBundle;

import application.AddNewLocationApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;

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
	@FXML
	private Label empty_text_field_error_label;
	
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
	 * The GUI calls this method when the appropriate button is pressed or when the enter key is pressed.
	 */
	@FXML
	private void updateNewLocation() {
		String newLocation = locationTextField.getText();
		if(newLocation.equals("")) { // Don't follow through if the user entered a blank string
			empty_text_field_error_label.setText("Please enter a location.");
		}else {
			application.setNewLocation(newLocation);
			stage.close();
		}
	}
	
	/**
	 * Method to close the stage that this controller is controlling.
	 * The GUI calls this method when the appropriate button is pressed. 
	 */
	@FXML
	private void closeStage() {
		stage.close();
	}
	
}
