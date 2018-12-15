package ui;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import application.AddNewLocationApplication;
import application.ApplicationFactory;
import application.DatabaseHandler;
import application.RidePost;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	private ComboBox<String> fromLocationComboBox;
	@FXML
	private ComboBox<String> toLocationComboBox;
	@FXML 
	private Button cancelButton;
	
	public AddRidePostController() {
		cancelButton = new Button();
	}
	
	/**
	 * Method to be called automatically when this controller is attached to the FXML file
	 */
	@FXML
	private void initialize() {
		initializeComboBoxes();
		cancelButton.setOnAction(new CancelButtonHandler());
	}
	
	/**
	 * Method to initialize the proper values of the location Combo Boxes, 
	 * based on the locations currently stored in the database
	 */
	@FXML
	private void initializeComboBoxes() {
		ArrayList<String> locations = DatabaseHandler.getLocations();
		resetComboBoxValues(fromLocationComboBox, locations);
		resetComboBoxValues(toLocationComboBox, locations);
		// The following listeners are added so that we can immediately know when the user selects the "Add a New Location..." option 
		fromLocationComboBox.valueProperty().addListener(new LocationComboBoxListener<String>(fromLocationComboBox));
		toLocationComboBox.valueProperty().addListener(new LocationComboBoxListener<String>(toLocationComboBox));
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
	
	private class CancelButtonHandler implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent event) {
			Application app = ApplicationFactory.getApplication(ApplicationFactory.ApplicationType.RIDE_LIST);
			try{
				app.start(primaryStage);
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
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
				AddNewLocationApplication newLocationApp = new AddNewLocationApplication(); // Can't use the ApplicationFactory because we need to call a method specific to AddNewLocationApplication
				newLocationApp.start(new Stage());
				String newLocation = newLocationApp.getNewLocation();
				if(newLocation == null) { // The user clicked cancel in the AddNewLocationGUI
					// comboBoxBeingObserved.getEditor().textProperty().setValue("Select a location...");;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							comboBoxBeingObserved.setValue((String) oldValue);
						}
					});
					return;
				}
				if(!DatabaseHandler.getLocations().contains(newLocation)) {
					DatabaseHandler.addLocation(newLocation);
					// Regardless of in which ComboBox this is happening, we now want to update the items in both ComboBoxes.
					// But we should preserve the current value of whichever ComboBox isn't involved at the moment!
					String oldFromValue = fromLocationComboBox.getValue();
					String oldToValue = toLocationComboBox.getValue();
					resetComboBoxValues(fromLocationComboBox, DatabaseHandler.getLocations());
					if(oldFromValue != null && !oldFromValue.equals(ADD_NEW_LOCATION_OPTION)) { // oldFromValue would be null if the from ComboBox hasn't been touched yet
						fromLocationComboBox.setValue(oldFromValue);
					}
					resetComboBoxValues(toLocationComboBox, DatabaseHandler.getLocations());
					if(oldToValue != null && !oldToValue.equals(ADD_NEW_LOCATION_OPTION)) {
						toLocationComboBox.setValue(oldToValue);
					}
				}
				comboBoxBeingObserved.setValue(newLocation);
			}
		}
		
	}
	
}
