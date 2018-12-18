package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RideDetailsController {
	private Stage primaryStage;
	
	@FXML
	private Label date;
	@FXML
	private Label time;
	@FXML
	private Label from;
	@FXML
	private Label to;
	@FXML
	private Label spots;
	@FXML
	private Label price;
	@FXML
	private Text comments;
	
	public RideDetailsController() {
		date = new Label();
		time = new Label();
		from = new Label();
		to = new Label();
		spots = new Label();
		price = new Label();
		comments = new Text();
	}
	/**
	 * Method to be called automatically when this controller is attached to the FXML file
	 */
	@FXML
	private void initialize() {
		
		//get all the values from the database tht are needed here
		//convert them to string maybe?
		
		date.setText();
		time.setText();
		from.setText();
		to.setText();
		spots.setText();
		price.setText();
		comments.setText();
	}
	
	
	
	
	
	
	
	
	
	
	
}
