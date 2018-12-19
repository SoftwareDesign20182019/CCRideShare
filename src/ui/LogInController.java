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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
/**
 * Connects the .fxml GUI file with the backend operations
 */
public class LogInController implements Controller
{
	private Stage stage;
	private DatabaseHandler databaseHandler;
	private ApplicationFactory appFactory;

	@FXML
	private PasswordField passwordfield;
	@FXML
	private TextField emailfield;
	@FXML
	private Button login;
	@FXML
	private Button createaccount;
	@FXML
    private Label emailNotExist;
    @FXML
    private Label wrongEmailPassword;

	public LogInController()
	{
		databaseHandler = DatabaseHandler.getInstance();
		passwordfield = new PasswordField();
		emailfield = new TextField();
		login = new Button();
		createaccount = new Button();
		emailNotExist = new Label();
		wrongEmailPassword = new Label();
	}

	/**
	 * Method to be called automatically when this controller is attached to the FXML file
	 */
	@FXML
	private void initialize() {

	}
/**
 * launches create account GUI
 */
	public void createAccountButton(){
		Application app = appFactory.getApplication(ApplicationFactory.ApplicationType.CREATE_ACCOUNT);
		try
		{
			app.start(stage);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
/**
 * logs in user by calling filter and check email methods to ensure credentials are correct
 * launches ridelist gui if successful	
 */
	public void logInButton()
	{
		String email = emailfield.getText();
		String password = passwordfield.getText();
		
		try
		{
			if(databaseHandler.checkEmail(email) && databaseHandler.isRightPassword(email,password))
			{
				databaseHandler.setCurrentUser(email);
				Application app = appFactory.getApplication(ApplicationFactory.ApplicationType.RIDE_LIST);
				app.start(stage);
				//TODO: Tell main GUI that this is the person logged in
				
			}
			else
			{
				emailNotExist.setVisible(false);
				wrongEmailPassword.setVisible(false);
				
				if(!databaseHandler.checkEmail(email))
				{
					emailNotExist.setVisible(true);
				}
				if(!databaseHandler.isRightPassword(email,password) && databaseHandler.checkEmail(email))
				{
					wrongEmailPassword.setVisible(true);
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		this.stage.setResizable(false);
	}
	
	public void setAppFactory(ApplicationFactory factory) {
		this.appFactory = factory;
	}
}
