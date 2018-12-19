package ui;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ResourceBundle;

import application.ApplicationFactory;
import application.DatabaseHandler;
import application.RideDetailsApplication;
import application.RideListApplication;
import application.RidePost;
import application.RideRequestPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Connects the .fxml GUI file with the backend operations
 * @author viktorkelemen & elymerenstein & arehorst
 */
public class RideListController implements Controller{
	
	private Stage stage;
	private DatabaseHandler databaseHandler;
	private ApplicationFactory appFactory;
	
	@FXML
    private Label currentDateLabel;
	
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
	@FXML
	private TabPane tabs;
	@FXML
	private Tab rides_tab;
	@FXML
	private Tab ride_requests_tab;
	
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
	@FXML
	private Button nextDay;
	@FXML
	private Button previousDay;
	@FXML
    private ChoiceBox<String> toLocationFilter;
    @FXML
    private ChoiceBox<String> fromLocationFilter;
    @FXML
    private DatePicker dateFilter;
    @FXML
    private ChoiceBox<String> seatsAvailableFilter;
    @FXML 
    private Button searchButton;
    @FXML
    private Button nextDayRequest;
    @FXML
    private Button previousDayRequest;
	@FXML
	private LocalDate displayDate;
	@FXML
	private Label currentDateRequest;
	
	public RideListController() {
		// Non-GUI
		databaseHandler = DatabaseHandler.getInstance();
		//Tab framework
		tabs = new TabPane();
		rides_tab = new Tab();
		ride_requests_tab = new Tab();
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
		//Other GUI
		addRidePostButton = new Button();
		addRideRequestButton = new Button();
		currentDateLabel = new Label();
		nextDay = new Button();
		previousDay = new Button();
		toLocationFilter = new ChoiceBox<String>();
		fromLocationFilter = new ChoiceBox<String>();
		seatsAvailableFilter = new ChoiceBox<String>();
		dateFilter = new DatePicker();
		searchButton = new Button();
		previousDayRequest = new Button();
		nextDayRequest = new Button();
		currentDateRequest = new Label();
		
	}
	
	/**
	 * Method to be called automatically when this controller is attached to the FXML file
	 */
	@FXML
	private void initialize() {
		
		toLocationFilter.getItems().addAll(databaseHandler.getLocations());
		fromLocationFilter.getItems().addAll(databaseHandler.getLocations());
		seatsAvailableFilter.getItems().addAll("0","1", "2", "3", "4", "5", "6", "7", "8", "9" , "10");
		// Ride Tab
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		Date date = Date.valueOf(localDate);
		
		time_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("time"));

		to_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("toLocation"));

		from_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("fromLocation"));

		avail_Seats_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("numSpots"));

		ridepost_table.setItems(rideData);

		displayDate = currentDate();
		rideData.addAll(databaseHandler.filterRidePosts(displayDate,null,null,null));
		requestData.addAll(databaseHandler.filterRequestPosts(displayDate,null,null));
		
		
		//Ride Request Tab
		request_time_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("time"));

		request_to_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("toLocation"));

		request_from_col.setCellValueFactory(new PropertyValueFactory<RidePost, String>("fromLocation"));

		riderequestpost_table.setItems(requestData);
		
		addRidePostButton.setOnAction(new AddRidePostButtonHandler());
		
		addRideRequestButton.setOnAction(new AddRideRequestButtonHandler());
		
		String dateLabel = formatter.format(localDate);
		currentDateLabel.setText(dateLabel);
		currentDateRequest.setText(dateLabel);
	}
	
	/**
	 * called when user clicks search button
	 * sets date as the displayed date if date filter is null
	 */
	public void clickedSearchButton()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = dateFilter.getValue();
		String dateLabel;
		
		if(date!= null) {
		displayDate = date;
		}
		
		dateLabel = formatter.format(displayDate);
		currentDateLabel.setText(dateLabel);
		currentDateRequest.setText(dateLabel);
		
		applyFilters();
	}
	
	public void setTab(RideListApplication.ListTab tab) {
		switch(tab) {
			case RIDES:
				tabs.getSelectionModel().select(rides_tab);
				break;
			case RIDE_REQUESTS:
				tabs.getSelectionModel().select(ride_requests_tab);
		}
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
		this.stage.setResizable(false);
	}
	
	public void setAppFactory(ApplicationFactory factory) {
		this.appFactory = factory;
	}
	
	private LocalDate currentDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		return localDate;
	}
	
	/**
	 * Called when next day button is clicked
	 * increments displayed date by one and updates list of ride posts according to the filter combo boxes
	 */
	public void nextDayButton() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		displayDate = displayDate.plusDays(1);
		String nextDate = formatter.format(displayDate);
		currentDateLabel.setText(nextDate);
		
		rideData.clear();
		
		String seats = seatsAvailableFilter.getValue();
		String destination = toLocationFilter.getValue();
		String from = fromLocationFilter.getValue();
		
		rideData.addAll(databaseHandler.filterRidePosts(displayDate, destination, from, seats));
	}
	
	/**
	 * Called when previous day button is clicked
	 * decrements displayed date by one and updates list of ride posts according to the filter combo boxes
	 */
	public void previousDayButton() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		displayDate = displayDate.minusDays(1);
		String nextDate = formatter.format(displayDate);
		currentDateLabel.setText(nextDate);
		
		rideData.clear();
		
		String seats = seatsAvailableFilter.getValue();
		String destination = toLocationFilter.getValue();
		String from = fromLocationFilter.getValue();
		
		rideData.addAll(databaseHandler.filterRidePosts(displayDate, destination, from, seats));
	}
	
	/**
	 * Called when next day button is clicked
	 * increments displayed date by one and updates list of request posts according to the filter combo boxes
	 */
	public void nextDayButtonRequest()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		displayDate = displayDate.plusDays(1);
		String nextDate = formatter.format(displayDate);
		currentDateRequest.setText(nextDate);
		
		requestData.clear();
		
		String destination = toLocationFilter.getValue();
		String from = fromLocationFilter.getValue();
		
		requestData.addAll(databaseHandler.filterRequestPosts(displayDate, destination, from));
	}
	
	/**
	 * Called when previous day button is clicked
	 * decrements displayed date by one and updates list of request posts according to the filter combo boxes
	 */
	public void previousDayButtonRequest()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		displayDate = displayDate.minusDays(1);
		String nextDate = formatter.format(displayDate);
		currentDateRequest.setText(nextDate);
		
		requestData.clear();
		
		String destination = toLocationFilter.getValue();
		String from = fromLocationFilter.getValue();
		
		requestData.addAll(databaseHandler.filterRequestPosts(displayDate, destination, from));
	}
	
	/**
	 * called by clickedSearchButton
	 * applies filters based on what is or isn't filled out in combo boxes by creating a new array list of posts that is copied from the arraylist of posts in 
	 * databaseHandler.filterRidePosts and databaseHandler.filterRideRequestPosts 
	 */
	public void applyFilters()
	{
		LocalDate date = dateFilter.getValue();
		String seats = seatsAvailableFilter.getValue();
		String destination = toLocationFilter.getValue();
		String from = fromLocationFilter.getValue();
		ArrayList<RidePost> addRides;
		ArrayList<RideRequestPost> addRequests;
		
		if(date==null)
		{
			addRides = databaseHandler.filterRidePosts(displayDate, destination, from, seats);
			addRequests = databaseHandler.filterRequestPosts(displayDate, destination, from);
		}
		else
		{
			addRides = databaseHandler.filterRidePosts(date,destination,from,seats);
			addRequests = databaseHandler.filterRequestPosts(date, destination, from);
		}
		
		rideData.clear();
		rideData.addAll(addRides);
		requestData.clear();
		requestData.addAll(addRequests);
	}
	
	private class AddRidePostButtonHandler implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent event) {
			Application app = appFactory.getApplication(ApplicationFactory.ApplicationType.ADD_RIDE_POST);
			try{
				app.start(stage);
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private class AddRideRequestButtonHandler implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent event) {
			Application app = appFactory.getApplication(ApplicationFactory.ApplicationType.ADD_RIDE_REQUEST);
			try{
				app.start(stage);
				
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	/**
	 * @return the ID of the selected post
	 */
	@FXML
	private void clickPost() {
		List<RidePost> postList;
		postList = ridepost_table.getSelectionModel().getSelectedItems();
		RidePost selectedPost = postList.get(0);
		
		// Can't use the ApplicationFactory because we need to call a method specific to AddNewLocationApplication
		RideDetailsApplication rideDetailsApp = new RideDetailsApplication(); 
		rideDetailsApp.setRidePost(selectedPost);
		rideDetailsApp.start(new Stage());
		
		
	}
}
