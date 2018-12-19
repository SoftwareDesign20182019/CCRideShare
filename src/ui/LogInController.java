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

public class LogInController 
{
	private Stage primaryStage;

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
    private DatabaseHandler database;

	public LogInController()
	{
		database = new DatabaseHandler();
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

	public void createAccountButton(){
		Application app = ApplicationFactory.getApplication(ApplicationFactory.ApplicationType.CREATE_ACCOUNT);
		try
		{
			app.start(primaryStage);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void logInButton()
	{
		String email = emailfield.getText();
		String password = passwordfield.getText();
		
		try
		{
			if(DatabaseHandler.checkEmail(email) && DatabaseHandler.isRightPassword(email,password))
			{
				DatabaseHandler.setCurrentUser(email);
				Application app = ApplicationFactory.getApplication(ApplicationFactory.ApplicationType.RIDE_LIST);
				app.start(primaryStage);
				//TODO: Tell main GUI that this is the person logged in
				
			}
			else
			{
				emailNotExist.setVisible(false);
				wrongEmailPassword.setVisible(false);
				
				if(!DatabaseHandler.checkEmail(email))
				{
					emailNotExist.setVisible(true);
				}
				if(!DatabaseHandler.isRightPassword(email,password) && DatabaseHandler.checkEmail(email))
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

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(false);
	}
}
