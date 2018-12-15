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
	@FXML
    private PasswordField passwordfield;

    @FXML
    private TextField emailfield;

    @FXML
    private Button login;

    @FXML
    private Button createaccount;
    
    public LogInController()
    {
    	passwordfield = new PasswordField();
    	emailfield = new TextField();
    	login = new Button();
    	createaccount = new Button();
    }
}
