package ui;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import application.AddNewLocationApplication;
import application.ApplicationFactory;
import application.DatabaseHandler;
import application.RideListApplication;
import application.RideRequestPost;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import java.util.ArrayList;
/**
 * Connects the AddRidePost FXML GUI file with the backend operations
 * @author elysamuel16
 */
public class AddRideRequestController implements Controller {

	@FXML
	private static final String ADD_NEW_LOCATION_OPTION = "Add a New Location...";
	
	private Stage stage;
	private DatabaseHandler databaseHandler;
	
	@FXML
	private URL location;
	@FXML
	private ResourceBundle resources;
	@FXML
	private ComboBox<String> from_location_combo_box;
	@FXML
	private ComboBox<String> to_location_combo_box;
	@FXML
	private DatePicker date;
	@FXML
	private ComboBox<Integer>time_hours;
	@FXML
	private ComboBox<String> time_minutes;
	@FXML 
	private ComboBox<String> time_ampm;
	@FXML
	private Label empty_box_errormessage;
	
	public AddRideRequestController() {
		databaseHandler = DatabaseHandler.getInstance();
		from_location_combo_box = new ComboBox<>();
		to_location_combo_box = new ComboBox<>();
		date = new DatePicker();
		time_hours = new ComboBox<>();		
		time_minutes = new ComboBox<>();
		time_ampm = new ComboBox<>();
		empty_box_errormessage = new Label();
	}
	
	/**
	 * Method to be called automatically when this controller is attached to the FXML file
	 */
	@FXML
	private void initialize() {
		time_hours.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
		time_minutes.getItems().addAll("00", "15", "30", "45");
		time_ampm.getItems().addAll("am","pm");
		empty_box_errormessage.setVisible(false);
		initializeLocationComboBoxes();		
	}
	
	/**
	 * Method to initialize the proper values of the location Combo Boxes, 
	 * based on the locations currently stored in the database
	 */
	@FXML
	private void initializeLocationComboBoxes() {
		ArrayList<String> locations = databaseHandler.getLocations();
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
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	@FXML
	private void createRideRequest() {
		empty_box_errormessage.setVisible(false);
		
		if(date.getValue() != null && (time_hours.getValue() != null) && (time_minutes.getValue() != null) && 
				(time_ampm.getValue() != null) && (to_location_combo_box.getValue() != null) && 
				(from_location_combo_box.getValue() != null))
		{					
			String time = "" + time_hours.getValue() + ":" + time_minutes.getValue() + time_ampm.getValue();
			
			RideRequestPost newRideRequest = new RideRequestPost(date.getValue().toString(), time, to_location_combo_box.getValue(), 
					from_location_combo_box.getValue());	
			
			databaseHandler.addRideRequestPost(newRideRequest);
			reopenRideListApp(RideListApplication.ListTab.RIDE_REQUESTS);
		}
		else {
			empty_box_errormessage.setVisible(true);
		}
	}
	
	@FXML
	private void cancelNewPost() {
		reopenRideListApp(RideListApplication.ListTab.RIDES);
	}
	
	private void reopenRideListApp(RideListApplication.ListTab tabDesired) {
		RideListApplication app = new RideListApplication(); // Can't use the factory this time because we need to call a method specific to RideListApplication
		app.setTab(tabDesired);
		try{
			app.start(stage);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Class for dealing with a change in a location combo box
	 * @author ely
	 * @param <T>
	 */
	private class LocationComboBoxListener<T> implements ChangeListener {

		private ComboBox<String> comboBoxBeingObserved;
		
		public LocationComboBoxListener(ComboBox<String> comboBoxBeingObserved) {
			this.comboBoxBeingObserved = comboBoxBeingObserved;
		}
		/**
		 * Deals with the ability of the user to add destinations not already in the list
		 * adds it to the database so next time the program is run, the new location will also be displayed
		 */
		@Override
		public void changed(ObservableValue observable, Object oldValue, Object newValue) {
			// observable.getValue() would be null during one of the below calls to resetComboBoxValues()
			// (since that method triggers the listener and thus calls this method recursively while the reset process is incomplete)
			if(observable.getValue() == null || !observable.getValue().equals(ADD_NEW_LOCATION_OPTION)) {  
																										 
				return;
			}
			// Can't use the ApplicationFactory because we need to call a method specific to AddNewLocationApplication
			AddNewLocationApplication newLocationApp = new AddNewLocationApplication(); 
			newLocationApp.start(new Stage());
			String newLocation = newLocationApp.getNewLocation();
			// The user clicked cancel in the AddNewLocationGUI
			if(newLocation == null) { 
				// comboBoxBeingObserved.getEditor().textProperty().setValue("Select a location...");;
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						comboBoxBeingObserved.setValue((String) oldValue);
					}
				});
				return;
			}
			if(!databaseHandler.getLocations().contains(newLocation)) {
				databaseHandler.addLocation(newLocation);
				// Regardless of in which ComboBox this is happening, we now want to update the items in both ComboBoxes.
				// But we should preserve the current value of whichever ComboBox isn't involved at the moment!
				String oldFromValue = from_location_combo_box.getValue();
				String oldToValue = to_location_combo_box.getValue();
				resetComboBoxValues(from_location_combo_box, databaseHandler.getLocations());
				// oldFromValue would be null if the from ComboBox hasn't been touched yet
				if(oldFromValue != null && !oldFromValue.equals(ADD_NEW_LOCATION_OPTION)) { 
					from_location_combo_box.setValue(oldFromValue);
				}
				resetComboBoxValues(to_location_combo_box, databaseHandler.getLocations());
				if(oldToValue != null && !oldToValue.equals(ADD_NEW_LOCATION_OPTION)) {
					to_location_combo_box.setValue(oldToValue);
				}
			}
			comboBoxBeingObserved.setValue(newLocation);
		}
		
	}
	
}
