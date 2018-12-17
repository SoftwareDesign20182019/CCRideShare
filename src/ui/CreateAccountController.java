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
import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javafx.application.Application;


public class CreateAccountController {

	/**
	 * Connects the .fxml GUI file with the backend operations
	 * @author arehorst
	 */
	private Stage primaryStage;

	// Ride Tab
	@FXML
	private URL location;
	@FXML
	private ResourceBundle resources;
	@FXML
	private PasswordField passwordfield;
	@FXML
	private TextField emailfield;
	@FXML
	private TextField namefield;
	@FXML
	private Button login;
	@FXML
	private Button createaccount;
    @FXML
    private Label checkEmailWarning;
    @FXML
    private Label emailFilterWarning;
    @FXML
    private Label emptyFieldWarning;
    @FXML
    private Label shortPasswordWarning;


	public CreateAccountController() {
		passwordfield = new PasswordField();
		emailfield = new TextField();
		namefield = new TextField();
		login = new Button();	
		createaccount = new Button();
		checkEmailWarning = new Label();
		emailFilterWarning = new Label();
		emptyFieldWarning = new Label();
		shortPasswordWarning = new Label();
	}

	/**
	 * Method to be called automatically when this controller is attached to the FXML file
	 */
	@FXML
	private void initialize() {
		
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(false);
	}
	/**
	 * when the login button is pressed
	 * brings up the login window
	 */
	public void logInButton() {
		Application app = ApplicationFactory.getApplication(ApplicationFactory.ApplicationType.LOG_IN);
		try{
			app.start(primaryStage);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * when the create account button is pressed
	 * checks for fields being empty, email being valid and not in use, password length
	 * displays error message accordingly
	 * if no errors, creates the user object and stores it in the database
	 * @throws Exception - because of app.start()
	 */
	public void createAccountButton() throws Exception {
		String password;
		String email;
		String fullName;
	
		password = passwordfield.getText();
		email = emailfield.getText();
		fullName = namefield.getText();
		
		emailFilterWarning.setVisible(false);
		checkEmailWarning.setVisible(false);
		emptyFieldWarning.setVisible(false);
		shortPasswordWarning.setVisible(false);
		
		//handling invalid and empty inputs
		if((DatabaseHandler.filterEmails(email)) && !(DatabaseHandler.checkEmail(email)) && !fullName.equals("") && !(password.length() < 5))
		{
			User newUser = new User(email,fullName);
			newUser.addToDatabase(password);
			ApplicationFactory.setCurrentUser(email);
			Application app = ApplicationFactory.getApplication(ApplicationFactory.ApplicationType.RIDE_LIST);
			app.start(primaryStage);
			
		}
		else if(DatabaseHandler.checkEmail(email))
		{
			checkEmailWarning.setVisible(true);
		}
		else if(fullName.equals("") || fullName.equals(null))
		{
			emptyFieldWarning.setVisible(true);
		}
		else if(password.length() < 5) {
			shortPasswordWarning.setVisible(true);
		}
		//if every input is valid, put in database
		else if(!(DatabaseHandler.filterEmails(email)))
		{
			emailFilterWarning.setVisible(true);	
		}
				
	}
	
	
}


