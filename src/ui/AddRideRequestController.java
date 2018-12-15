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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.ArrayList;
/**
 * Connects the AddRidePost FXML GUI file with the backend operations
 * @author elysamuel16
 */
public class AddRideRequestController{

	private Stage primaryStage;
	
	@FXML
	private URL location;
	@FXML
	private ResourceBundle resources;
	@FXML 
	private Button cancelButton;
	
	public AddRideRequestController() {
		cancelButton = new Button();
	}
	
	/**
	 * Method to be called automatically when this controller is attached to the FXML file
	 */
	@FXML
	private void initialize() {
		cancelButton.setOnAction(new cancelButtonHandler());
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	private class cancelButtonHandler implements EventHandler<ActionEvent>{
		
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
	
}
