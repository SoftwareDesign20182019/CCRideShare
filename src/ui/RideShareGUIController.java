package ui;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ResourceBundle;

import application.DatabaseHandler;
import application.RidePost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.ArrayList;
/**
 * Connects the .fxml GUI file with the backend oprations
 * @author viktorkelemen & elymerenstein
 */
public class RideShareGUIController{

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
	ObservableList<RidePost> data;
	
	
	public RideShareGUIController() {
		application.Main.primaryStage.setResizable(false);
		ridepost_table = new TableView<RidePost>();
		time_col = new TableColumn();
		to_col = new TableColumn();
		from_col = new TableColumn();
		avail_Seats_col = new TableColumn();
		data = FXCollections.observableArrayList();
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
	}
	
//	public void addToRidePostList(RidePost ridePost) {
//		data.add(ridePost);
//	}
//	
//	public void addAllToRidePostList(ArrayList<RidePost> ridePosts) {
//		data.addAll(ridePosts);
//	}
	
	
}
