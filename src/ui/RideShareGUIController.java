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
 * @author viktorkelemen & elymerenstein
 */
public class RideShareGUIController{

	private Stage primaryStage;
	
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
	private ObservableList<RidePost> data;
	@FXML
	private Button addRidePostButton;
	
	
	public RideShareGUIController() {
		ridepost_table = new TableView<RidePost>();
		time_col = new TableColumn();
		to_col = new TableColumn();
		from_col = new TableColumn();
		avail_Seats_col = new TableColumn();
		data = FXCollections.observableArrayList();
		addRidePostButton = new Button();
	}
	
	/**
	 * Method to be called automatically when this controller is attached to the FXML file
	 */
	@FXML
	private void initialize() {
		
		time_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("time"));
		
		to_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("toLocation"));
		
		from_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("fromLocation"));
		
		avail_Seats_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("numSpots"));
		
		ridepost_table.setItems(data);
		
		ArrayList<RidePost> ridePosts = DatabaseHandler.getRidePosts();
		data.addAll(ridePosts);
		
		addRidePostButton.setOnAction(new addRidePostButtonHandler());
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
//	public void addToRidePostList(RidePost ridePost) {
//		data.add(ridePost);
//	}
//	
//	public void addAllToRidePostList(ArrayList<RidePost> ridePosts) {
//		data.addAll(ridePosts);
//	}
	
	
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
	
	
}
