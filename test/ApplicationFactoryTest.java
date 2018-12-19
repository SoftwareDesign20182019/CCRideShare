import static org.junit.Assert.*;

import org.junit.Test;

import application.AddRidePostApplication;
import application.AddRideRequestApplication;
import application.ApplicationFactory;
import application.ApplicationFactory.ApplicationType;
import application.CreateAccountApplication;
import application.LogInApplication;
import application.RideListApplication;

public class ApplicationFactoryTest {

	@Test
	public void testGetApplicationRidelist() {
		ApplicationType testType = ApplicationFactory.ApplicationType.RIDE_LIST;
		assertTrue(ApplicationFactory.getApplication(testType) instanceof RideListApplication);		
	}
	@Test
	public void testGetApplicationAddridepost() {
		ApplicationType testType = ApplicationFactory.ApplicationType.ADD_RIDE_POST;
		assertTrue(ApplicationFactory.getApplication(testType) instanceof AddRidePostApplication);		
	}
	@Test
	public void testGetApplicationAddriderequest() {
		ApplicationType testType = ApplicationFactory.ApplicationType.ADD_RIDE_REQUEST;
		assertTrue(ApplicationFactory.getApplication(testType) instanceof AddRideRequestApplication);		
	}
	@Test
	public void testGetApplicationCreateaccount() {
		ApplicationType testType = ApplicationFactory.ApplicationType.CREATE_ACCOUNT;
		assertTrue(ApplicationFactory.getApplication(testType) instanceof CreateAccountApplication);		
	}
	@Test
	public void testGetApplicationLogin() {
		ApplicationType testType = ApplicationFactory.ApplicationType.LOG_IN;
		assertTrue(ApplicationFactory.getApplication(testType) instanceof LogInApplication);		
	}
	
	

}
