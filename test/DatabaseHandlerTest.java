import application.DatabaseHandler;
import application.RidePost;
import application.RideRequestPost;
import application.User;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatabaseHandlerTest {

	@Test
	public void addRideTest() 
	{
		DatabaseHandler handler = new DatabaseHandler();
		handler.initialize();
		RidePost ridePost = new RidePost("1-1-1", "11:11PM", "Danver", "Colorado College", 4, 20, "no comments");
		int rowsAdded = ridePost.addToDatabase();
		
		assertEquals(rowsAdded,1);
	}
	
	@Test
	public void addRideRequestTest() 
	{
		DatabaseHandler handler = new DatabaseHandler();
		handler.initialize();
		RideRequestPost rideRequest = new RideRequestPost("1-1-1", "11:11PM", "CC", "Denver");
		int rowsAdded = rideRequest.addToDatabase();
		
		assertEquals(rowsAdded,1);
	}
	
	@Test
	public void addLocationTest() {
		DatabaseHandler handler = new DatabaseHandler();
		handler.initialize();
		String location = "The best location";
		int rowsAdded = handler.addLocation(location);
		
		assertEquals(rowsAdded,1);
	}
	
	@Test
	public void addAccountTest() {
		DatabaseHandler handler = new DatabaseHandler();
		handler.initialize();
		User user = new User("accounttest@coloradocollege.edu", "Account Tester");
		String password = "testpassword";
		int rowsAdded = handler.addAccount(user, password);
		
		assertEquals(rowsAdded,1);
	}
	
	@Test
	public void validEmailTest() {
		DatabaseHandler handler = new DatabaseHandler();
		handler.initialize();
		String email = "testemail@coloradocollege.edu";
		boolean emailIsValid = handler.filterEmails(email);
		assertTrue(emailIsValid);
	}
	
	@Test
	public void invalidEmailTest() {
		DatabaseHandler handler = new DatabaseHandler();
		handler.initialize();
		String email = "thebestemail";
		boolean emailIsValid = handler.filterEmails(email);
		assertFalse(emailIsValid);
	}
	
	@Test
	public void doesContainEmailTest() {
		DatabaseHandler handler = new DatabaseHandler();
		handler.initialize();
		String email = "containsemailtest@coloradocollege.edu";
		User user = new User(email, "Contains Email Tester");
		String password = "testpassword";
		handler.addAccount(user, password);
		boolean containsEmail = handler.checkEmail(email);
		assertTrue(containsEmail);
	}
	
	@Test
	public void rightPasswordTest() {
		DatabaseHandler handler = new DatabaseHandler();
		handler.initialize();
		String email = "rightpasswordtest@coloradocollege.edu";
		User user = new User(email, "Right Password Tester");
		String password = "testpassword";
		handler.addAccount(user, password);
		boolean isRightPassword = handler.isRightPassword(email, password);
		assertTrue(isRightPassword);
	}

}
