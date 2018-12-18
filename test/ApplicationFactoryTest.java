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
		GeneralApplication testApp = ApplicationFactory.getApplication(testType);
		try{
			testApp.start(new Stage());
			assertTrue(testApp.getController() instanceof RideListController);		
		}catch(Exception ex) {
			ex.printStackTrace();
			fail("Exception thrown");
		}
	}
	@Test
	public void testGetApplicationAddridepost() {
		ApplicationType testType = ApplicationFactory.ApplicationType.ADD_RIDE_POST;
		GeneralApplication testApp = ApplicationFactory.getApplication(testType);
		assertTrue(testApp.getController() instanceof AddRidePostController);		
	}
	@Test
	public void testGetApplicationAddriderequest() {
		ApplicationType testType = ApplicationFactory.ApplicationType.ADD_RIDE_REQUEST;
		GeneralApplication testApp = ApplicationFactory.getApplication(testType);
		assertTrue(testApp.getController() instanceof AddRideRequestController);			
	}
	@Test
	public void testGetApplicationCreateaccount() {
		ApplicationType testType = ApplicationFactory.ApplicationType.CREATE_ACCOUNT;
		GeneralApplication testApp = ApplicationFactory.getApplication(testType);
		assertTrue(testApp.getController() instanceof CreateAccountController);		
	}
	@Test
	public void testGetApplicationLogin() {
		ApplicationType testType = ApplicationFactory.ApplicationType.LOG_IN;
		GeneralApplication testApp = ApplicationFactory.getApplication(testType);
		assertTrue(testApp.getController() instanceof LogInController);		
	}
	
	

}
