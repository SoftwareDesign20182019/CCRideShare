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
		RidePost ridePost = new RidePost("1-1-1", "11:11PM", "Danver", "Colorado College", 4, 20, "no comments");
		int rowsAdded = ridePost.addToDatabase();
		
		assertEquals(rowsAdded,1);
	}
	
	@Test
	public void addRideRequestTest() 
	{
		RideRequestPost rideRequest = new RideRequestPost("1-1-1", "11:11PM", "CC", "Denver");
		int rowsAdded = rideRequest.addToDatabase();
		
		assertEquals(rowsAdded,1);
	}
	
	@Test
	public void addLocationTest() {
		DatabaseHandler dbHandler = DatabaseHandler.getInstance();
		String location = "The best location";
		int rowsAdded = dbHandler.addLocation(location);
		
		assertEquals(rowsAdded,1);
	}
	
	@Test
	public void addAccountTest() {
		DatabaseHandler dbHandler = DatabaseHandler.getInstance();
		User user = new User("accounttest@coloradocollege.edu", "Account Tester");
		String password = "testpassword";
		int rowsAdded = dbHandler.addAccount(user, password);
		
		assertEquals(rowsAdded,1);
	}
	
	@Test
	public void validEmailTest() {
		DatabaseHandler dbHandler = DatabaseHandler.getInstance();
		String email = "testemail@coloradocollege.edu";
		boolean emailIsValid = dbHandler.filterEmails(email);
		assertTrue(emailIsValid);
	}
	
	@Test
	public void invalidEmailTest() {
		DatabaseHandler dbHandler = DatabaseHandler.getInstance();
		String email = "thebestemail";
		boolean emailIsValid = dbHandler.filterEmails(email);
		assertFalse(emailIsValid);
	}
	
	@Test
	public void doesContainEmailTest() {
		DatabaseHandler dbHandler = DatabaseHandler.getInstance();
		String email = "containsemailtest@coloradocollege.edu";
		User user = new User(email, "Contains Email Tester");
		String password = "testpassword";
		dbHandler.addAccount(user, password);
		boolean containsEmail = dbHandler.checkEmail(email);
		assertTrue(containsEmail);
	}
	
	@Test
	public void rightPasswordTest() {
		DatabaseHandler dbHandler = DatabaseHandler.getInstance();
		String email = "rightpasswordtest@coloradocollege.edu";
		User user = new User(email, "Right Password Tester");
		String password = "testpassword";
		dbHandler.addAccount(user, password);
		boolean isRightPassword = dbHandler.isRightPassword(email, password);
		assertTrue(isRightPassword);
	}

}
