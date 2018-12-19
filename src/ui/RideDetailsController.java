package ui;
	
	import application.RidePost;
	import javafx.fxml.FXML;
	import javafx.scene.control.Label;
	import javafx.scene.text.Text;
	import javafx.stage.Stage;

/**
 * Conencts the Ride Details application with its GUI, initializing values properly 
 * @author viktor
 *
 */
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
		private RidePost ridePost;
		@FXML
		private Label email;
	
		public RideDetailsController() {
			date = new Label();
			time = new Label();
			from = new Label();
			to = new Label();
			spots = new Label();
			price = new Label();
			comments = new Text();
			email = new Label();
		}
		/**
		 * Method to be called automatically when this controller is attached to the FXML file
		 */
		@FXML
		private void initialize() {
	
		}
		/**
		 * returns a String with the value of the desired attribute of the RidePost
		 */
		public void setRidePost(RidePost ridePost) {
			this.ridePost = ridePost;
		}
	
		public void setStage(Stage stage) {
			this.primaryStage = stage;
		}
	
		/**
		 * Properly initialize GUI values (since putting these initializations in the initialize method is too early and results in errors)
		 */
		public void assignGUIvalues() {
			date.setText(ridePost.getDate());
			time.setText(ridePost.getTime());
			from.setText(ridePost.getFromLocation());
			to.setText(ridePost.getToLocation());
			spots.setText(Integer.toString(ridePost.getNumSpots()));
			price.setText(Integer.toString(ridePost.getPrice()));
			comments.setText(ridePost.getComments());
			//implement this
			email.setText(ridePost.getEmail());
		}
	
	
	
	
	
	
	
	}