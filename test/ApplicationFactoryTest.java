import static org.junit.Assert.*;

import org.junit.Test;

import javafx.stage.Stage;
import ui.RideListController;
import ui.AddRidePostController;
import ui.AddRideRequestController;
import ui.LogInController;
import ui.CreateAccountController;
import application.ApplicationFactory;
import application.ApplicationFactory.ApplicationType;
import application.GeneralApplication;

public class ApplicationFactoryTest {

	@Test
	public void testGetApplicationRidelist() {
		ApplicationType testType = ApplicationType.RIDE_LIST;
		ApplicationFactory appFactory = new ApplicationFactory();
		GeneralApplication testApp = appFactory.getApplication(testType);
		assertTrue(testApp.getGUIPath().contains("ui/RideListGUI.fxml"));
	}
	@Test
	public void testGetApplicationAddridepost() {
		ApplicationType testType = ApplicationFactory.ApplicationType.ADD_RIDE_POST;
		ApplicationFactory appFactory = new ApplicationFactory();
		GeneralApplication testApp = appFactory.getApplication(testType);
		assertTrue(testApp.getGUIPath().contains("ui/AddRidePostGUI.fxml"));		
	}
	@Test
	public void testGetApplicationAddriderequest() {
		ApplicationType testType = ApplicationFactory.ApplicationType.ADD_RIDE_REQUEST;
		ApplicationFactory appFactory = new ApplicationFactory();
		GeneralApplication testApp = appFactory.getApplication(testType);
		assertTrue(testApp.getGUIPath().contains("ui/AddRideRequestGUI.fxml"));			
	}
	@Test
	public void testGetApplicationCreateaccount() {
		ApplicationType testType = ApplicationFactory.ApplicationType.CREATE_ACCOUNT;
		ApplicationFactory appFactory = new ApplicationFactory();
		GeneralApplication testApp = appFactory.getApplication(testType);
		assertTrue(testApp.getGUIPath().contains("ui/CreateAccountGUI.fxml"));		
	}
	@Test
	public void testGetApplicationLogin() {
		ApplicationType testType = ApplicationFactory.ApplicationType.LOG_IN;
		ApplicationFactory appFactory = new ApplicationFactory();
		GeneralApplication testApp = appFactory.getApplication(testType);
		assertTrue(testApp.getGUIPath().contains("ui/LogInGUI.fxml"));		
	}
	
	

}
