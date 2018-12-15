package ui;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ResourceBundle;

import application.AddRidePostApplication;
import application.ApplicationFactory;
import application.DatabaseHandler;
import application.RideListApplication;
import application.RidePost;
import application.RideRequestPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 * Connects the .fxml GUI file with the backend operations
 * @author viktorkelemen & elymerenstein & arehorst
 */
public class RideShareGUIController{
	private Stage primaryStage;
	
	// Ride Tab
	@FXML
	private URL location;
	@FXML
	private ResourceBundle resources;
	@FXML
	private TableView<RidePost> ridepost_table;
	@FXML
	private TableColumn time_col;
	@FXML
	private TableColumn to_col;
	@FXML
	private TableColumn from_col;
	@FXML
	private TableColumn avail_Seats_col;
	@FXML
	private ObservableList<RidePost> rideData;
	
	// Ride Request Tab
	@FXML
	private TableView<RideRequestPost> riderequestpost_table;
	@FXML
	private TableColumn request_time_col;
	@FXML
	private TableColumn request_to_col;
	@FXML
	private TableColumn request_from_col;
	@FXML
	private ObservableList<RideRequestPost> requestData;
	
	// Other
	@FXML
	private Button addRidePostButton;
	@FXML
	private Button addRideRequestButton;
	
	
	public RideShareGUIController() {
		//Ride Tab
		ridepost_table = new TableView<RidePost>();
		time_col = new TableColumn();
		to_col = new TableColumn();
		from_col = new TableColumn();
		avail_Seats_col = new TableColumn();
		rideData = FXCollections.observableArrayList();
		//Ride Request Tab
		riderequestpost_table = new TableView<RideRequestPost>();
		request_time_col = new TableColumn();
		request_to_col = new TableColumn();
		request_from_col = new TableColumn();
		requestData = FXCollections.observableArrayList();
		//Other
		addRidePostButton = new Button();
		addRideRequestButton = new Button();
	}

	/**
	 * Method to be called automatically when this controller is attached to the FXML file
	 */
	@FXML
	private void initialize() {
		// Ride Tab
		time_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("time"));

		to_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("toLocation"));

		from_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("fromLocation"));

		avail_Seats_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("numSpots"));

		ridepost_table.setItems(rideData);

		ArrayList<RidePost> ridePosts = DatabaseHandler.getRidePosts();
		rideData.addAll(ridePosts);

		//Ride Request Tab
		request_time_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("time"));

		request_to_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("toLocation"));

		request_from_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("fromLocation"));

		riderequestpost_table.setItems(requestData);

		ArrayList<RideRequestPost> riderequestPosts = DatabaseHandler.getRideRequestPosts();
		requestData.addAll(riderequestPosts);
		
		addRidePostButton.setOnAction(new addRidePostButtonHandler());
		
		addRideRequestButton.setOnAction(new addRideRequestButtonHandler());
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(false);
	}
	
	private class addRidePostButtonHandler implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent event) {
			Application app = ApplicationFactory.getApplication(ApplicationFactory.ApplicationType.ADD_RIDE_POST);
			try{
				app.start(primaryStage);
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private class addRideRequestButtonHandler implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent event) {
			Application app = ApplicationFactory.getApplication(ApplicationFactory.ApplicationType.ADD_RIDE_REQUEST);
			try{
				app.start(primaryStage);
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
}
