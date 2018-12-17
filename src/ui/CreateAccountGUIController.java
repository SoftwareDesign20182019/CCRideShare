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


public class CreateAccountGUIController {

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
    


	public CreateAccountGUIController() {
		passwordfield = new PasswordField();
		emailfield = new TextField();
		namefield = new TextField();
		login = new Button();	
		createaccount = new Button();
		checkEmailWarning = new Label();
		emailFilterWarning = new Label();
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

	public void logInButton() {
		Application app = ApplicationFactory.getApplication(ApplicationFactory.ApplicationType.LOG_IN);
		try{
			app.start(primaryStage);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void createAccountButton() {
		String password;
		String email;
		String fullName;
		
		try
		{
			password = passwordfield.getText();
			email = emailfield.getText();
			fullName = namefield.getText();
			
			if(DatabaseHandler.filterEmails(email) && !DatabaseHandler.checkEmail(email))
			{
				User newUser = new User(email,fullName);
				newUser.addToDatabase(password);
				ApplicationFactory.setCurrentUser(email);
				System.out.println("Current user is: "+ApplicationFactory.getCurrentUser().getFullName());
				Application app = ApplicationFactory.getApplication(ApplicationFactory.ApplicationType.RIDE_LIST);
				app.start(primaryStage);
			}
			else
			{
				emailFilterWarning.setVisible(false);
				checkEmailWarning.setVisible(false);

				if(!DatabaseHandler.filterEmails(email))
				{
					emailFilterWarning.setVisible(true);
				}
				if(DatabaseHandler.checkEmail(email))
				{
					checkEmailWarning.setVisible(true);
				}
			}
				//User newUser = new User(email,password,fullName);
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}
}


