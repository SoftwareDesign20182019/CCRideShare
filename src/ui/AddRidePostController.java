package ui;
import java.net.URL;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import application.ApplicationFactory;
import application.DatabaseHandler;
import application.RidePost;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Connects the AddRidePost FXML GUI file with the backend operations
 * @author elysamuel16
 */
public class AddRidePostController{

	private Stage primaryStage;
	
	@FXML
	private static final String ADD_NEW_LOCATION_OPTION = "Add a New Location..."; 
	
	@FXML
	private URL location;
	@FXML
	private ResourceBundle resources;
	@FXML
	private ComboBox<String> from_location_combo_box;
	@FXML
	private ComboBox<String> to_location_combo_box;
	@FXML 
	private Button cancel_button;
	@FXML
	private DatePicker date;
	@FXML
	private TextField time_hours;
	@FXML
	private TextField time_minutes;
	@FXML 
	private TextField time_ampm;
	@FXML
	private TextField num_available_spots;
	@FXML
	private TextField price;
	@FXML
	private TextArea comments;
	
	public AddRidePostController() {
		cancel_button = new Button();
		from_location_combo_box = new ComboBox<>();
		to_location_combo_box = new ComboBox<>();
		date = new DatePicker();
		time_hours = new TextField();
		time_minutes = new TextField();
		time_ampm = new TextField();
		num_available_spots = new TextField();
		price = new TextField();
		comments = new TextArea();
	}
	
	/**
	 * creates a RidePost object from user input
	 * 	calls addRidePost to add it to database
	 * called when the SUBMIT button is clicked
	 */
	public void createRidePost() {
		String time = "" + time_hours.getText() + ":" + time_minutes.getText() + time_ampm.getText();
		
		RidePost newRidePost = new RidePost(date.getValue().toString(), time, to_location_combo_box.getValue(), 
				from_location_combo_box.getValue(), Integer.parseInt(num_available_spots.getText()), price.getText(), comments.getText());	
		
		DatabaseHandler.addRidePost(newRidePost);
		reopenRideListApp();
	}
	
	/**
	 * Method to be called automatically when this controller is attached to the FXML file
	 */
	@FXML
	private void initialize() {
		initializeComboBoxes();		
	}
	
	/**
	 * Method to initialize the proper values of the location Combo Boxes, 
	 * based on the locations currently stored in the database
	 */
	@FXML
	private void initializeComboBoxes() {
		ArrayList<String> locations = DatabaseHandler.getLocations();
		resetComboBoxValues(from_location_combo_box, locations);
		resetComboBoxValues(to_location_combo_box, locations);
		// The following listeners are added so that we can immediately know when the user selects the "Add a New Location..." option 
		from_location_combo_box.valueProperty().addListener(new LocationComboBoxListener<String>(from_location_combo_box));
		to_location_combo_box.valueProperty().addListener(new LocationComboBoxListener<String>(to_location_combo_box));
	}
	
	/**
	 * Method to reset the values of the given location ComboBox to the given 
	 * list of locations, plus the constant string for the add new location option
	 * @param locationComboBox the ComboBox whose values will be reset
	 * @param locations the list of locations to use for resetting the ComboBox's values
	 */
	public void resetComboBoxValues(ComboBox<String> locationComboBox, ArrayList<String> locations) {
		locationComboBox.getItems().removeAll(locationComboBox.getItems());
		locationComboBox.getItems().addAll(locations);
		locationComboBox.getItems().add(ADD_NEW_LOCATION_OPTION);
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void reopenRideListApp() {
		Application app = ApplicationFactory.getApplication(ApplicationFactory.ApplicationType.RIDE_LIST);
		try{
			app.start(primaryStage);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		
		}
	}
	
	private class LocationComboBoxListener<T> implements ChangeListener {

		private ComboBox<String> comboBoxBeingObserved;
		
		public LocationComboBoxListener(ComboBox<String> comboBoxBeingObserved) {
			this.comboBoxBeingObserved = comboBoxBeingObserved;
		}
		
		@Override
		public void changed(ObservableValue observable, Object oldValue, Object newValue) {
			if(observable.getValue() != null && observable.getValue().equals(ADD_NEW_LOCATION_OPTION)) { // observable.getValue() would be null during one of the below calls to resetComboBoxValues() 
																										 // (since that method triggers the listener and thus calls this method recursively while the reset process is incomplete)
				String newLocation = null; // TODO: Update this to actually get the string from the user
				if(!DatabaseHandler.getLocations().contains(newLocation)) {
					DatabaseHandler.addLocation(newLocation);
					// Regardless of in which ComboBox this is happening, we now want to update the items in both ComboBoxes.
					// But we should preserve the current value of whichever ComboBox isn't involved at the moment!
					String oldFromValue = from_location_combo_box.getValue();
					String oldToValue = to_location_combo_box.getValue();
					resetComboBoxValues(from_location_combo_box, DatabaseHandler.getLocations());
					if(oldFromValue != null && !oldFromValue.equals(ADD_NEW_LOCATION_OPTION)) { // oldFromValue would be null if the from ComboBox hasn't been touched yet
						from_location_combo_box.setValue(oldFromValue);
					}
					resetComboBoxValues(to_location_combo_box, DatabaseHandler.getLocations());
					if(oldToValue != null && !oldToValue.equals(ADD_NEW_LOCATION_OPTION)) {
						to_location_combo_box.setValue(oldToValue);
					}
				}
				comboBoxBeingObserved.setValue(newLocation);
			}
		}
		
	}
	
}
